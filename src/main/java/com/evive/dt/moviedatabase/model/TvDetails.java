package com.evive.dt.moviedatabase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TvDetails {
  @JsonProperty("id")
  private int tvId;

  @Override
  public String toString() {
    return "tvId=" + tvId;
  }
}
