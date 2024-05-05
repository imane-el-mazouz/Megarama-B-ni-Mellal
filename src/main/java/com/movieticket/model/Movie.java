package com.movieticket.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "bg_img_url")
    private String bgImgUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Column(name = "duration")
    private Time duration;

    @Column(name = "price")
    private int price;

    @Column(name = "rating")
    private int rating;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "show_time")
    private Time showTime;

    @Column(name = "show_date")
    private Date showDate;
    public enum Genre {
        ACTION, COMEDY, DRAMA, HORROR, SCIENCE, ROMANCE,
    }

    public enum Language{
        ar, en, fr,
    }

    public Movie() {
    }

    public Movie(int movieId, String imgUrl, String bgImgUrl, String title, String description, Genre genre, Language language, Time duration, int price, int rating, int numberOfSeats, Time showTime, Date showDate) {
        this.movieId = movieId;
        this.imgUrl = imgUrl;
        this.bgImgUrl = bgImgUrl;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.price = price;
        this.rating = rating;
        this.numberOfSeats = numberOfSeats;
        this.showTime = showTime;
        this.showDate = showDate;
    }


    public Movie(String imgUrl, String bgImgUrl, String title, String description, Genre genre, Language language, Time duration, int price, int rating, int numberOfSeats, Time showTime, Date showDate) {

        this.imgUrl = imgUrl;
        this.bgImgUrl = bgImgUrl;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.price = price;
        this.rating = rating;
        this.numberOfSeats = numberOfSeats;
        this.showTime = showTime;
        this.showDate = showDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBgImgUrl() { return bgImgUrl; }

    public void setBgImgUrl(String bgImgUrl) { this.bgImgUrl = bgImgUrl; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Time getShowTime() {
        return showTime;
    }

    public void setShowTime(Time showTime) {
        this.showTime = showTime;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }
}

