package com.iti.onlinestore;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderInfoModel {

//    ArrayList<OrderInfo> results;
//    @SerializedName("total_pages")
//    String totalPages;
    @SerializedName("date")
    String OrderDate;
    @SerializedName("order_id")
    String orderId;
    String state;
    @SerializedName("expected_delivery_date")
    String expectedDate;

    public String getOrderDate() {
        return OrderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getState() {
        return state;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    //    public ArrayList<OrderInfo> getMovies() {
//        return results;
//    }
//
//    public void setResults(ArrayList<OrderInfo> results) {
//        this.results = results;
//    }
//
//    public String getTotal_pages() {
//        return totalPages;
//    }
//
//    public void setTotal_pages(String total_pages) {
//        this.totalPages = total_pages;
//    }
}
