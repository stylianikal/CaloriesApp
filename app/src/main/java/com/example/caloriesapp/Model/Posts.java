package com.example.caloriesapp.Model;

public class Posts {
    public String date;
    public String description;
    public String fullname;
    public String phone;
    public String postimage;
    public String profileimage;
    public String time;

    public Posts(){

    }

    public Posts(String date, String description, String fullname, String phone, String postimage, String profileimage, String time) {
        this.date = date;
        this.description = description;
        this.fullname = fullname;
        this.phone = phone;
        this.postimage = postimage;
        this.profileimage = profileimage;
        this.time = time;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
