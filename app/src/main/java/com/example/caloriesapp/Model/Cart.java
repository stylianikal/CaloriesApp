package com.example.caloriesapp.Model;

public class Cart
{
    private String pid, name, calories, quantity, discount, result;

    public Cart() {
    }

    public Cart(String pid, String name, String calories, String quantity, String discount, String result) {
        this.pid = pid;
        this.name = name;
        this.calories = calories;
        this.quantity = quantity;
        this.discount = discount;
        this.result = result;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getname() {
        return name;
    }

    public void setPname(String pname) {
        this.name = pname;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result= result;
    }
}
