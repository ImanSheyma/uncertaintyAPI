package com.api.uncertainty.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SummaryIndexService {
    private EconomicAreaService economicAreaService;
    private QuestionService questionService;
    private IndexService indexService;

}
