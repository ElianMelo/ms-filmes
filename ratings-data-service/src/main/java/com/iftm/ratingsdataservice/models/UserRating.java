package com.iftm.ratingsdataservice.models;

import java.util.Arrays;
import java.util.List;

public class UserRating {

    private String userId;
    private List<Rating> ratings;

    public void initData(String userId) {
        this.setUserId(userId);
        this.setRatings(Arrays.asList(
                new Rating("550", 3),
                new Rating("268", 4),
                new Rating("78", 5)
        ));
    }
    public String getUserId() {return userId;}
	public void setUserId(String userId) {this.userId = userId;}
    public List<Rating> getRatings() {return ratings;}
    public void setRatings(List<Rating> ratings) {this.ratings = ratings;}
}