package com.api.uncertainty.controller;

import com.api.uncertainty.services.EconomicAreaService;
import com.api.uncertainty.services.IndexService;
import com.api.uncertainty.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uncertainty/")
public class Controller {
    private final EconomicAreaService economicAreaService;
    private final IndexService indexService;
    private final QuestionService questionService;


}
