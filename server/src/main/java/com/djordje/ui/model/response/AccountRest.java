package com.djordje.ui.model.response;

public class AccountRest {

    private String accountId;
    private String type;
    private String name;

    private CustomerRest customer;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

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

    public CustomerRest getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRest customer) {
        this.customer = customer;
    }
}
