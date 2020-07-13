package com.djordje.shared.dto;

import java.util.List;

public class AccountDTO {


    private Long id;
    private String accountId;
    private String type;
    private String name;

    private CustomerDTO customer;
    private List<FarmDTO> farms;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<FarmDTO> getFarms() {
        return farms;
    }

    public void setFarms(List<FarmDTO> farms) {
        this.farms = farms;
    }
}
