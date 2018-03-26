package com.moviedb.util;

public enum Type {
     MOVIE("movie"),
     DOCUMENTARY("documentary");

     String type;

     Type(String type){
         this.type = type;
     }
}
