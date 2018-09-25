package com.evive.dt.moviedatabase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TvCastResponse {
  @JsonProperty("id")
  private int tvId;

  @JsonProperty("cast")
  private List<TvCastDetails> castResponse;
}
