package com.api.uncertainty.controller;

import com.api.uncertainty.exceptions.AreaNotFoundException;
import com.api.uncertainty.models.Index;
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
    public ResponseEntity<List<Index>> getSummaryIndexes() throws AreaNotFoundException {
        List<Index> summaryIndexes = summaryIndexService.getSummaryIndexes();
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("area/{area}")
    public ResponseEntity<List<Index>> getSummaryIndexesByArea(
            @PathVariable("area") String area) throws AreaNotFoundException {
        List<Index> summaryIndexes = summaryIndexService.getSummaryIndexesByAreaName(area);
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("date-range")
    public ResponseEntity<List<Index>> getSummaryIndexesByDateRange(
            @RequestParam("dateStart")Date dateStart,
            @RequestParam("dateEnd") Date dateEnd){
        List<Index> summaryIndexes = summaryIndexService.getSummaryIndexesByDateRange(
                dateStart,dateEnd);
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("date-range/area/{area}")
    public ResponseEntity<List<Index>> getSummaryIndexesByAreaAndDateRange(
            @PathVariable("area") String area,
            @RequestParam("dateStart") Date dateStart,
            @RequestParam("dateEnd") Date dateEnd){
        List<Index> summaryIndexes = summaryIndexService
                .getSummaryIndexesByAreaAndDateRange(dateStart, dateEnd, area);
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @ExceptionHandler(AreaNotFoundException.class)
    public ResponseEntity<String> handleAreaNotFoundException(AreaNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
