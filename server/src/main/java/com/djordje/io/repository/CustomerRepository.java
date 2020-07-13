package com.djordje.io.repository;

import com.djordje.io.entity.Customer;
import com.djordje.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
