package com.api.uncertainty.services;

import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.models.Index;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class SummaryIndexService {
    private final EconomicAreaService economicAreaService;
    private final QuestionService questionService;
    private final UncertaintyIndexService uncertaintyIndexService;

    public List<Index> getIndexesByArea(EconomicArea area) {
        List<Question> questions = questionService.findAllByArea(area);
        List<List<Index>> uiLists = new ArrayList<>();
        for(Question question: questions){
            uiLists.add(uncertaintyIndexService.findAllByQuestion(question));
        }
        List<Index> indexes = normolize(computeIndex(uiLists));
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
        List<Index> indexes = normolize(computeIndex(listOfAreaIndexes));
        return indexes;
    }

    public List<Index> getIndexesByDateRange(Date dateStart, Date dateEnd){
        List<EconomicArea> economicAreas = economicAreaService.findAll();
        List<List<Index>> listOfAreaIndexes = new ArrayList<>();
        for(EconomicArea area: economicAreas){
            List<Index> areaIndexes = getIndexesByAreaAndDateRange(area, dateStart, dateEnd);
            for(Index index: areaIndexes)
                index.setValue(index.getValue() * area.getWeight());
            listOfAreaIndexes.add(areaIndexes);
        }
        List<Index> indexes = normolize(computeIndex(listOfAreaIndexes));
        return indexes;
    }

    public List<Index> getIndexesByAreaAndDateRange(EconomicArea area,
                                                    Date dateStart,
                                                    Date dateEnd){
        List<Question> questions = questionService.findAllByArea(area);

        List<List<Index>> uiLists = new ArrayList<>();
        for(Question question: questions){
            uiLists.add(uncertaintyIndexService
                    .findAllByQuestionAndDateRange(question, dateStart, dateEnd));
        }

        List<Index> indexes = normolize(computeIndex(uiLists));
        return indexes;
    }


    //private methods
    //
    private List<Index> computeIndex(List<List<Index>> indexes){
        List<Index> summaryIndexes = new ArrayList<>();
        Map<Date, Double> sums = getIndexesSumsMap(indexes);
        List<Date> keySet = new ArrayList<>(sums.keySet());
        Collections.sort(keySet);
        for(Date key: keySet){
            Index summaryIndex = new Index(sums.get(key), key);
            summaryIndexes.add(summaryIndex);
        }
        return summaryIndexes;
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
        std = Math.pow(std/(n-1), 0.5);
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
