package com.api.uncertainty.services;

import com.api.uncertainty.exceptions.AreaNotFoundException;
import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.models.Index;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class SummaryIndexService {
    private EconomicAreaService economicAreaService;
    private QuestionService questionService;
    private UncertaintyIndexService uncertaintyIndexService;

    public List<Index> getIndexesByAreaName(String area) throws AreaNotFoundException {
        Optional<EconomicArea> areaOptional = economicAreaService.findByAreaName(area);
        EconomicArea economicArea = areaOptional.orElseThrow(()->new AreaNotFoundException(area));
        return getIndexesByArea(economicArea);
    }

    public List<Index> getIndexesByArea(EconomicArea area) {
        List<Question> questions = questionService.findAllByArea(area);
        List<List<Index>> uiLists = new ArrayList<>();
        for(Question question: questions){
            uiLists.add(uncertaintyIndexService.findAllByQuestion(question));
        }
        List<Index> indexes = normolize(countIndex(uiLists));
        return indexes;
    }

    public List<Index> getIndexes(){
        List<EconomicArea> economicAreas = economicAreaService.findAll();
        List<List<Index>> listOfAreaIndexes = new ArrayList<>();
        for(EconomicArea area: economicAreas){
            List<Index> areaIndexes = getIndexesByArea(area);
            for(Index index: areaIndexes)
                index.setValue(index.getValue() * area.getWeight());
            listOfAreaIndexes.add(areaIndexes);
        }
        List<Index> indexes = normolize(countIndex(listOfAreaIndexes));
        return indexes;
    }

    public List<Index> getIndexesByDateRange(Date dateStart, Date dateEnd){
        List<Index> summaryIndexes;
        return null;
    }

    public List<Index> getIndexesByAreaAndDateRange(Date dateStart,
                                                    Date dateEnd,
                                                    String area){
     List<Index> summaryIndexe;
     return null;
    }


    //private methods
    //
    private List<Index> countIndex(List<List<Index>> indexes){
        List<Index> summaryIndices = new ArrayList<>();
        Map<Date, Double> sums = getIndexesSumsMap(indexes);
        Set<Date> keySet = sums.keySet();
        for(Date key: keySet){
            Index summaryIndex = new Index(sums.get(key), key);
            summaryIndices.add(summaryIndex);
        }
        return null;
    }

    private List<Index> normolize(List<Index> indexes){
        double sum = 0, mean, std = 0;
        int n = indexes.size();
        List<Index> result = new ArrayList<>();
        for(Index index: indexes)
            sum += index.getValue();
        mean = sum / n;
        for(Index index: indexes)
            std += Math.pow(index.getValue() - mean, 2);
        std = Math.pow(std/(n-1), 1/2);
        for(Index index: indexes) {
            Index normolizedIndex = new Index();
            normolizedIndex.setValue((index.getValue() - mean) / std * 10 + 100);
            normolizedIndex.setDate(index.getDate());
            result.add(normolizedIndex);
        }
        return result;
    }

    //sums of values by date
    private Map<Date, Double> getIndexesSumsMap(List<List<Index>> uiLists){
        Map<Date, Double> sums = new HashMap<>();
        for(List<Index> uiList: uiLists)
            for(Index ui: uiList){
                Date date = ui.getDate();
                if(sums.containsKey(ui.getDate()))
                    sums.replace(date, sums.get(date) + ui.getValue());
                else sums.put(date, ui.getValue());
            }
        return sums;
    }
}
