package com.djordje.ui.model.request;

public class AccountDetailsRequestModel {

    private String type;
    private String name;

    private CustomerDetailsRequestModel customer;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerDetailsRequestModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDetailsRequestModel customer) {
        this.customer = customer;
    }


}
