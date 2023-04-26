package com.api.uncertainty.services;

import com.api.uncertainty.exceptions.AreaNotFoundException;
import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.models.Index;
import com.api.uncertainty.models.UncertaintyIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class SummaryIndexService {
    private EconomicAreaService economicAreaService;
    private QuestionService questionService;
    private UncertaintyIndexService uncertaintyIndexService;

    public List<Index> getSummaryIndexesByAreaName(String area) throws AreaNotFoundException {
        Optional<EconomicArea> areaOptional = economicAreaService.findByAreaName(area);
        EconomicArea economicArea = areaOptional.orElseThrow(()->new AreaNotFoundException(area));
        return getSummaryIndexesByArea(economicArea);
    }

    public List<Index> getSummaryIndexesByArea(EconomicArea area) throws AreaNotFoundException {
        List<Question> questions = questionService.findAllByArea(area);
        List<List<Index>> uiLists = new ArrayList<>();
        for(Question question: questions){
            uiLists.add(uncertaintyIndexService.findAllByQuestion(question));
        }
        return countAreaSummaryIndex(uiLists);
    }

    public List<Index> getSummaryIndexes() throws AreaNotFoundException {
        List<EconomicArea> economicAreas = economicAreaService.findAll();
        List<List<Index>> areaIndexes = new ArrayList<>();
        for(EconomicArea area: economicAreas){
            areaIndexes.add(getSummaryIndexesByArea(area));
        }
        return countSummaryIndex(areaIndexes);
    }

    public List<Index> getSummaryIndexesByDateRange(Date dateStart, Date dateEnd){
        List<Index> summaryIndexes;
        return null;
    }

    public List<Index> getSummaryIndexesByAreaAndDateRange(Date dateStart,
                                                           Date dateEnd,
                                                           String area){
     List<Index> summaryIndexe;
     return null;
    }

    public List<Index> getSummaryIndexesByExpactationQuestions(){
        return null;
    }

    public List<Index> getSummaryIndexesByExpactationQuestionsAndDateRange(Date dateStart,
                                                                           Date dateEnd){
        return null;
    }

    private List<Index> countSummaryIndex(List<List<Index>> areaIndexes){
        List<Index> summaryIndices = new ArrayList<>();
        Map<Date, Double> sums = getIndexesSumsMap(areaIndexes);

        Set<Date> keySet = sums.keySet();
        Map<Date, Double> normolizedIndexes = normolize(sums);
        for(Date key: keySet){
            Index summaryIndex = new Index(normolizedIndexes.get(key), key);
            summaryIndices.add(summaryIndex);
        }
        return null;
    }

    private List<Index> countAreaSummaryIndex(List<List<Index>> uiLists){
        List<Index> summaryIndices = new ArrayList<>();
        Map<Date, Double> sums = getIndexesSumsMap(uiLists);

        Set<Date> keySet = sums.keySet();
        Map<Date, Double> normolizedIndexes = normolize(sums);
        for(Date key: keySet){
            Index summaryIndex = new Index(normolizedIndexes.get(key), key);
            summaryIndices.add(summaryIndex);
        }
        return summaryIndices;
    }

    private Map<Date, Double> normolize(Map<Date, Double> series){
        double sum = 0, mean, std = 0;
        int n = series.size();
        Map<Date, Double> result = new HashMap<>();
        Set<Date> keySet = series.keySet();
        for(Date key: keySet)
            sum += series.get(key);
        mean = sum / series.size();
        for(Date key: keySet)
            std += Math.pow(series.get(key) - mean, 2);
        std = Math.pow(std/(n-1), 1/2);
        for(Date key: keySet)
            result.put(key, Double.valueOf((series.get(key) - mean)/std * 10 + 100));
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
