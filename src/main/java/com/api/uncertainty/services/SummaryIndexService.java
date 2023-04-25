package com.api.uncertainty.services;

import com.api.uncertainty.models.SummaryIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SummaryIndexService {
    private EconomicAreaService economicAreaService;
    private QuestionService questionService;
    private IndexService indexService;

    public List<SummaryIndex> getSummaryIndexesByArea(String area){
        List<SummaryIndex> summaryIndexes;
        return null;
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
                                                                  Date dateEnd, String area){
     List<SummaryIndex> summaryIndexe;
     return null;
    }
}
