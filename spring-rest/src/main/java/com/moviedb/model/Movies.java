package com.moviedb.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
public class Movies {

//    private String _id;
    private String title;
    private int year;
    private String imdb;
    private String type;

    public Movies() {
    }

    public Movies(/*String _id,*/ String title, int year, String imdb, String type) {
//        this._id = _id;
        this.title = title;
        this.year = year;
        this.imdb = imdb;
        this.type = type;
    }

/*
    public String get_id() {
        return _id;
    }

    public Movies set_id(String _id) {
        this._id = _id;
        return this;
    }
*/

    public String getTitle() {
        return title;
    }

    public Movies setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Movies setYear(int year) {
        this.year = year;
        return this;
    }

    public String getImdb() {
        return imdb;
    }

    public Movies setImdb(String imdb) {
        this.imdb = imdb;
        return this;
    }

    public String getType() {
        return type;
    }

    public Movies setType(String type) {
        this.type = type;
        return this;
    }
}
