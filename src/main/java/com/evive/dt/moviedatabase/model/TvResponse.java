package com.evive.dt.moviedatabase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TvResponse implements Serializable {
  private int page;
  @JsonProperty("total_results")
  private int totalResults;
  @JsonProperty("total_pages")
  private int totalPages;
  @JsonProperty("results")
  private List<TvDetails> tvResults;

  @Override
  public String toString() {
    return "DiscoverTvResponse{" +
        "page=" + page +
        ", totalResults=" + totalResults +
        ", totalPages=" + totalPages +
        ", tvResults=" + tvResults +
        '}';
  }
}
