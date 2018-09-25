package com.evive.dt.moviedatabase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieCastDetails {
  @JsonProperty("cast_id")
  private int castId;
  @JsonProperty("character")
  private String character;
  @JsonProperty("id")
  private int nameId;
  @JsonProperty("name")
  private String name;
}
