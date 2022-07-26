package com.example.caloriesapp.Model;

public class AdminPlans
{
    private String name, phone, email, date, time, totalCalories, result;

    //constructor

    public AdminPlans() {
    }

    public AdminPlans(String name, String phone, String email, String date, String time, String totalCalories, String result) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.time = time;
        this.totalCalories = totalCalories;
        this.result = result;
    }

    //get and set method for each field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(String totalCalories) {
        this.totalCalories = totalCalories;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
