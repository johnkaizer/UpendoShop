package com.project.upendoshop.Models;

public class CustomerOrderModel {
    String OrderItems;
    String OrderAmount;
    String Date;
    String CustName;
    String PhoneNumber;
    String Location;

    public CustomerOrderModel() {
    }

    public CustomerOrderModel(String orderItems, String orderAmount, String date, String custName, String phoneNumber, String location) {
        OrderItems = orderItems;
        OrderAmount = orderAmount;
        Date = date;
        CustName = custName;
        PhoneNumber = phoneNumber;
        Location = location;
    }

    public String getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(String orderItems) {
        OrderItems = orderItems;
    }

    public String getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        OrderAmount = orderAmount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
