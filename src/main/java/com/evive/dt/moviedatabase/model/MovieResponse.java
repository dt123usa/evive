package com.evive.dt.moviedatabase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MovieResponse implements Serializable {
  private int page;
  @JsonProperty("total_results")
  private int totalResults;
  @JsonProperty("total_pages")
  private int totalPages;
  @JsonProperty("results")
  private List<MovieDetails> movieResults;

  @Override
  public String toString() {
    return "DiscoverMovieResponse{" +
        "page=" + page +
        ", totalResults=" + totalResults +
        ", totalPages=" + totalPages +
        ", movieResults=" + movieResults +
        '}';
  }
}
