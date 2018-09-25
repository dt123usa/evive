package com.evive.dt.moviedatabase.dao;

import com.evive.dt.moviedatabase.model.TvResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;


@Component
public class DiscoverTvDao {

  private String baseTvUrl = "http://api.themoviedb.org/3/discover/tv?api_key=606aaffd7ca10f0b80804a1f0674e4e1";

  public TvResponse getTvList(LocalDate startDate, LocalDate endDate, int page) {

    RestTemplate restTemplate = new RestTemplate();
    String fullTvUrl = baseTvUrl + "&first_air_date.gte=" + startDate + "&first_air_date.lte=" + endDate + "&page=" + page;

    TvResponse discoverTvResponse = null;
    boolean success = false;
    while (!success) {
      try {
        discoverTvResponse = restTemplate.getForObject(fullTvUrl, TvResponse.class);
        success = true;
      } catch (HttpClientErrorException e) {
        System.out.println("TEST: " + e.toString() + " - " + e.getMessage() + " - " + e.getStatusText() + " - " + e.getResponseBodyAsString() + " - " + e.getLocalizedMessage());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
    }

    return discoverTvResponse;

  }
}
