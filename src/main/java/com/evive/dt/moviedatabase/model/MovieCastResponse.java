package com.evive.dt.moviedatabase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovieCastResponse {

  @JsonProperty("id")
  private int movieId;

  @JsonProperty("cast")
  private List<MovieCastDetails> castDetails;
}

