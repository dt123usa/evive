package com.evive.dt.moviedatabase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieDetails {
  @JsonProperty("id")
  private int movieId;

  @Override
  public String toString() {
    return "movieId=" + movieId;
  }
}
