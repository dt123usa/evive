package com.evive.dt.moviedatabase.service;

import com.evive.dt.moviedatabase.dao.DiscoverMovieCastDao;
import com.evive.dt.moviedatabase.dao.DiscoverMovieDao;
import com.evive.dt.moviedatabase.dao.DiscoverTvCastDao;
import com.evive.dt.moviedatabase.dao.DiscoverTvDao;
import com.evive.dt.moviedatabase.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class MovieDatabaseService {

  @Autowired
  private DiscoverMovieDao discoverMovieDao;

  @Autowired
  private DiscoverMovieCastDao discoverMovieCastDao;

  @Autowired
  private DiscoverTvDao discoverTvDao;

  @Autowired
  private DiscoverTvCastDao discoverTvCastDao;

  private int movieNameCount = 0;
  private int tvNameCount = 0;

  public int getMatchingNamesCount(LocalDate startDate, LocalDate endDate){

    movieNameCount = 0;
    tvNameCount = 0;
    Set<String> movieNameSet = movieNames(startDate, endDate);
    Set<String> tvNameSet = tvNames(startDate, endDate);

    System.out.println("Movie Size: " + movieNameSet.size());
    System.out.println("   TV Size: " + tvNameSet.size());

    movieNameSet.retainAll(tvNameSet);

    return movieNameSet.size();
  }

  private Set<String> movieNames(LocalDate startDate, LocalDate endDate){
    MovieResponse movieResponse = discoverMovieDao.getMovieList(startDate, endDate, 1);
    Set<String> movieCastNames = new HashSet<>();
    movieCastNames.addAll(getMovieCast(movieResponse));

    int totalPages = movieResponse.getTotalPages();

    if(totalPages > 1000){
      //process more than 1000 pages
      for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
      {
        totalPages = 1;
        for (int i = 1; i <= totalPages && i < 1001; i++){
          movieResponse = discoverMovieDao.getMovieList(startDate, endDate, i);
          totalPages = movieResponse.getTotalPages();
          movieCastNames.addAll(getMovieCast(movieResponse));
        }
      }
    }else{
      //process less than 1000 pages
      for (int i = 2; i <= totalPages; i++){
        movieResponse = discoverMovieDao.getMovieList(startDate, endDate, i);
        movieCastNames.addAll(getMovieCast(movieResponse));
      }
    }
    return movieCastNames;
  }

  private Set<String> getMovieCast(MovieResponse movieResponse){
    Set<String> movieCastNames = new HashSet<>();
    for (MovieDetails movieDetails:movieResponse.getMovieResults()){
      MovieCastResponse movieCastResponse = discoverMovieCastDao.getMovieCastList(movieDetails.getMovieId());
      for (MovieCastDetails movieCastDetails: movieCastResponse.getCastDetails()){
        System.out.println(++movieNameCount + ": " + movieCastDetails.getName()  + " Page: " + movieResponse.getPage());
        movieCastNames.add(movieCastDetails.getName());
      }
    }
    return movieCastNames;
  }


  private Set<String> tvNames(LocalDate startDate, LocalDate endDate){
    TvResponse tvResponse = discoverTvDao.getTvList(startDate, endDate, 1);
    Set<String> tvCastNames = new HashSet<>();
    tvCastNames.addAll(getTvCast(tvResponse));

    int totalPages = tvResponse.getTotalPages();

    if(totalPages > 1000){
      //process more than 1000 pages
      for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
      {
        totalPages = 1;
        for (int i = 1; i <= totalPages; i++){
          tvResponse = discoverTvDao.getTvList(startDate, endDate, i);
          totalPages = tvResponse.getTotalPages();
          tvCastNames.addAll(getTvCast(tvResponse));
        }
      }
    }else{
      //process less than 1000 pages
      for (int i = 2; i <= totalPages && i < 1001; i++){
        tvResponse = discoverTvDao.getTvList(startDate, endDate, i);
        tvCastNames.addAll(getTvCast(tvResponse));
      }
    }
    return tvCastNames;
  }

  private Set<String> getTvCast(TvResponse tvResponse){
    Set<String> tvCastNames = new HashSet<>();
    for (TvDetails tvDetails:tvResponse.getTvResults()){
      TvCastResponse tvCastResponse = discoverTvCastDao.getTvCastList(tvDetails.getTvId());
      for (TvCastDetails tvCastDetails: tvCastResponse.getCastResponse()){
        System.out.println(++tvNameCount + ": " + tvCastDetails.getName()  + " Page: " + tvResponse.getPage());
        tvCastNames.add(tvCastDetails.getName());
      }
    }
    return tvCastNames;
  }
}
