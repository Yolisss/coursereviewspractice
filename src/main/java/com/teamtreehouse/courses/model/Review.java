package com.teamtreehouse.courses.model;


import java.util.Objects;

public class Review {
    private int id;
    private int courseId;
    private int rating;
    private String comment;

    public Review(int courseId, int rating, String comment) {
        this.courseId = courseId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;
        return id == review.id && courseId == review.courseId && rating == review.rating && comment.equals(review.comment);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + courseId;
        result = 31 * result + rating;
        result = 31 * result + comment.hashCode();
        return result;
    }
}
