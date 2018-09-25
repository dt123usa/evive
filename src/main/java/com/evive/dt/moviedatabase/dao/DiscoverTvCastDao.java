package com.evive.dt.moviedatabase.dao;

import com.evive.dt.moviedatabase.model.TvCastResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class DiscoverTvCastDao {

  public TvCastResponse getTvCastList(int tvId) {

    RestTemplate restTemplate = new RestTemplate();
    String fullTvUrl = "https://api.themoviedb.org/3/tv/" + tvId + "/credits?api_key=606aaffd7ca10f0b80804a1f0674e4e1";

    TvCastResponse tvCastResponse = null;
    boolean success = false;
    while (!success) {
      try {
        tvCastResponse = restTemplate.getForObject(fullTvUrl, TvCastResponse.class);
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
    return tvCastResponse;
  }
}
