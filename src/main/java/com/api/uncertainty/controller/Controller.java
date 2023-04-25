package com.api.uncertainty.controller;

import com.api.uncertainty.models.SummaryIndex;
import com.api.uncertainty.services.SummaryIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uncertainty/")
public class Controller {
    private final SummaryIndexService summaryIndexService;

    @GetMapping("EUI")
    public ResponseEntity<List<SummaryIndex>> getSummaryIndexes(){
        List<SummaryIndex> summaryIndexes = summaryIndexService.getSummaryIndexes();
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("area/{area}")
    public ResponseEntity<List<SummaryIndex>> getSummaryIndexesByArea(
            @PathVariable("area") String area){
        List<SummaryIndex> summaryIndexes = summaryIndexService.getSummaryIndexesByArea(area);
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("date-range")
    public ResponseEntity<List<SummaryIndex>> getSummaryIndexesByDateRange(
            @RequestParam("dateStart")Date dateStart,
            @RequestParam("dateEnd") Date dateEnd){
        List<SummaryIndex> summaryIndexes = summaryIndexService.getSummaryIndexesByDateRange(
                dateStart,dateEnd);
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("date-range/area/{area}")
    public ResponseEntity<List<SummaryIndex>> getSummaryIndexesByAreaAndDateRange(
            @PathVariable("area") String area,
            @RequestParam("dateStart") Date dateStart,
            @RequestParam("dateEnd") Date dateEnd){
        List<SummaryIndex> summaryIndexes = summaryIndexService
                .getSummaryIndexesByAreaAndDateRange(dateStart, dateEnd, area);
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }
}
