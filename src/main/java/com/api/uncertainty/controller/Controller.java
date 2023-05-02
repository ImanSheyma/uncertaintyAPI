package com.api.uncertainty.controller;

import com.api.uncertainty.exceptions.AreaNotFoundException;
import com.api.uncertainty.models.EconomicArea;
import com.api.uncertainty.models.Index;
import com.api.uncertainty.services.EconomicAreaService;
import com.api.uncertainty.services.SummaryIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uncertainty/")
public class Controller {
    private final SummaryIndexService summaryIndexService;
    private final EconomicAreaService economicAreaService;

    @GetMapping("EUI")
    public ResponseEntity<List<Index>> getSummaryIndexes() {
        List<Index> summaryIndexes = summaryIndexService.getIndexes();
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("{area}")
    public ResponseEntity<List<Index>> getSummaryIndexesByArea(
            @PathVariable("area") String areaName) throws AreaNotFoundException {

        Optional<EconomicArea> areaOptional = economicAreaService.findByAreaName(areaName);
        EconomicArea area = areaOptional.orElseThrow(()->new AreaNotFoundException(areaName));

        List<Index> summaryIndexes = summaryIndexService.getIndexesByArea(area);

        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("EUI/date-range")
    public ResponseEntity<List<Index>> getSummaryIndexesByDateRange(
            @RequestParam("dateStart")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateStart,
            @RequestParam("dateEnd")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateEnd){
        List<Index> summaryIndexes = summaryIndexService
                .getIndexesByDateRange(dateStart,dateEnd);
        return new ResponseEntity<>(summaryIndexes, HttpStatus.OK);
    }

    @GetMapping("{area}/date-range")
    public ResponseEntity<List<Index>> getSummaryIndexesByAreaAndDateRange(
            @PathVariable("area") String areaName,
            @RequestParam("dateStart")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateStart,
            @RequestParam("dateEnd")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateEnd) throws AreaNotFoundException {

        Optional<EconomicArea> areaOptional = economicAreaService.findByAreaName(areaName);
        EconomicArea area = areaOptional.orElseThrow(()->new AreaNotFoundException(areaName));

        List<Index> summaryIndexes = summaryIndexService
                .getIndexesByAreaAndDateRange(area, dateStart, dateEnd);

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
