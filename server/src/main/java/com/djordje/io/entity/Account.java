package com.djordje.io.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private String accountId;
    private String type;
    private String name;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Farm> farms = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserEntity userEntity;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Customer customer;


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

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;

        if (userEntity != null && !userEntity.getAccounts().contains(this)) {
            userEntity.getAccounts().add(this);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public void addFarm(Farm farm) {
        this.farms.add(farm);

        if (!this.equals(farm.getAccount())) {
            farm.setAccount(this);
        }
    }
}
