package com.evive.dt.moviedatabase.dao;

import com.evive.dt.moviedatabase.model.MovieResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
public class DiscoverMovieDao {

  private String baseMovieUrl = "http://api.themoviedb.org/3/discover/movie?api_key=606aaffd7ca10f0b80804a1f0674e4e1";

  public MovieResponse getMovieList(LocalDate startDate, LocalDate endDate, int page) {

    RestTemplate restTemplate = new RestTemplate();
    String fullMovieUrl = baseMovieUrl + "&release_date.gte=" + startDate + "&release_date.lte=" + endDate + "&page=" + page;

    MovieResponse discoverMovieResponse = null;
    boolean success = false;
    while (!success) {
      try {
        discoverMovieResponse = restTemplate.getForObject(fullMovieUrl, MovieResponse.class);
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

    return discoverMovieResponse;

  }
}
