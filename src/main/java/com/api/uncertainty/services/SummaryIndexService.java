package com.api.uncertainty.services;

import com.api.uncertainty.exceptions.AreaNotFoundException;
import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.models.Question;
import com.api.uncertainty.models.SummaryIndex;
import com.api.uncertainty.models.UncertaintyIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SummaryIndexService {
    private EconomicAreaService economicAreaService;
    private QuestionService questionService;
    private UncertaintyIndexService uncertaintyIndexService;

    public List<SummaryIndex> getSummaryIndexesByArea(String area) throws AreaNotFoundException {
        Optional<EconomicArea> areaOptional = economicAreaService.findByArea(area);
        EconomicArea economicArea = areaOptional.orElseThrow(()->new AreaNotFoundException(area));
        List<Question> questions = questionService.findAllByArea(economicArea);
        List<List<UncertaintyIndex>> uiLists = new ArrayList<>();
        for(Question question: questions){
            uiLists.add(uncertaintyIndexService.findAllByQuestion(question));
        }
        return countSummaryIndex(uiLists, economicArea);
    }

    public List<SummaryIndex> getSummaryIndexes(){
        List<SummaryIndex> summaryIndexes;
        return null;
    }

    public List<SummaryIndex> getSummaryIndexesByDateRange(Date dateStart, Date dateEnd){
        List<SummaryIndex> summaryIndexes;
        return null;
    }

    public List<SummaryIndex> getSummaryIndexesByAreaAndDateRange(Date dateStart,
                                                                  Date dateEnd,
                                                                  String area){
     List<SummaryIndex> summaryIndexe;
     return null;
    }

    public List<SummaryIndex> getSummaryIndexesByExpactationQuestions(){
        return null;
    }

    public List<SummaryIndex> getSummaryIndexesByExpactationQuestionsAndDateRange(Date dateStart,
                                                                                  Date dateEnd){
        return null;
    }

    private List<SummaryIndex> countSummaryIndex(List<List<UncertaintyIndex>> uiLists, EconomicArea area){
        List<SummaryIndex> summaryIndices = new ArrayList<>(uiLists.get(0).size());
        return summaryIndices;
    }

    private List<Double> normolize(List<Double> series){
        double sum = 0, mean, std = 0;
        int n = series.size();
        List<Double> normolizedSeries = new ArrayList<>();
        for(Double value: series)
            sum += value;
        mean = sum / series.size();
        for(Double value: series)
            std += Math.pow(value - mean, 2);
        std = Math.pow(std/(n-1), 1/2);
        for(Double value: series)
            normolizedSeries.add(Double.valueOf((value - mean)/std * 10 + 100));
        return normolizedSeries;
    }
}
