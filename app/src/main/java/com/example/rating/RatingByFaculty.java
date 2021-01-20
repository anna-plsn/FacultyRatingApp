package com.example.rating;

public class RatingByFaculty {

    String name,group;
    int rating;
    String student_id;

    public RatingByFaculty() {
    }

    public RatingByFaculty(String student_id, String name, String group, int rating) {
        this.name = name;
        this.group = group;
        this.student_id = student_id;
        this.rating = rating;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup (String group) {
        this.group = group;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = getRating();
    }
}
