package com.project.upendoshop.Models;

public class CartModels {
    private int id;
    String title;
    String quantity;
    String pcs;
    String amount;

    public CartModels(int id, String title, String quantity, String pcs, String amount) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.pcs = pcs;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
