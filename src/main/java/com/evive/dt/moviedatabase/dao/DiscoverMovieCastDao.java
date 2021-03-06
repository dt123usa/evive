package com.evive.dt.moviedatabase.dao;

import com.evive.dt.moviedatabase.model.MovieCastResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class DiscoverMovieCastDao {

  public MovieCastResponse getMovieCastList(int movieId) {

    RestTemplate restTemplate = new RestTemplate();
    String fullMovieUrl = "https://api.themoviedb.org/3/movie/" + movieId + "/credits?api_key=606aaffd7ca10f0b80804a1f0674e4e1";

    MovieCastResponse discoverMovieCastResponse = null;
    boolean success = false;
    while (!success) {
      try {
        discoverMovieCastResponse = restTemplate.getForObject(fullMovieUrl, MovieCastResponse.class);
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
    return discoverMovieCastResponse;
  }
}

