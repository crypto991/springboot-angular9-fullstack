package com.djordje.service;

import com.djordje.shared.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO getCustomer(String accountId);
}
