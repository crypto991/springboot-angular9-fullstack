package com.djordje.io.repository;

import com.djordje.io.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountId(String id);

    void deleteAccountByAccountId(String id);

}
