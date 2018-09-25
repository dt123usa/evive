package com.evive.dt.moviedatabase.controller;

import com.evive.dt.moviedatabase.service.MovieDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class movieDatabaseController {

  @Autowired
  private MovieDatabaseService movieDatabaseService;

  @GetMapping ("/compare/cast")
  public int castCompare(
      @RequestParam(value = "start_date", required=false, defaultValue = "2017-12-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate ,
      @RequestParam(value = "end_date", required=false, defaultValue = "2017-12-02") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate ){
    return movieDatabaseService.getMatchingNamesCount(startDate, endDate);
  }
}
