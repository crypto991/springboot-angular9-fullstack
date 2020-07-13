package com.djordje;

import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.io.entity.Farm;
import com.djordje.io.entity.UserEntity;
import com.djordje.io.repository.AccountRepository;
import com.djordje.io.repository.CustomerRepository;
import com.djordje.io.repository.FarmRepository;
import com.djordje.io.repository.UserRepository;
import com.djordje.service.UserService;
import com.djordje.shared.Utils;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.dto.CustomerDTO;
import com.djordje.shared.dto.FarmDTO;
import com.djordje.shared.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RestApiTestApplication implements CommandLineRunner {

    @Autowired
    private Utils utils;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(RestApiTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@gmail.com");
        userEntity.setFirstName("John");
        userEntity.setLastName("Jones");
        userEntity.setEncryptedPassword(passwordEncoder.encode("123456"));
        userEntity.setUserId(utils.generateUserId(30));

        //farms
        Farm farm1 = new Farm();
        farm1.setFarmId(utils.generateAccountId(30));
        farm1.setName("farma1");

        Farm farm2 = new Farm();
        farm2.setFarmId(utils.generateAccountId(30));
        farm2.setName("farma2");

        Farm farm3 = new Farm();
        farm3.setFarmId(utils.generateAccountId(30));
        farm3.setName("farma3");

        Farm farm4 = new Farm();
        farm4.setFarmId(utils.generateAccountId(30));
        farm4.setName("farma4");

        //customers
        Customer customer1 = new Customer();
        customer1.setCustomerId(utils.generateCustomerId(30));
        customer1.setName("Customer1");

        Customer customer2 = new Customer();
        customer2.setCustomerId(utils.generateCustomerId(30));
        customer2.setName("Customer2");

        //accounts
        Account account1 = new Account();
        account1.setAccountId(utils.generateAccountId(30));
        account1.setName("account1");
        account1.setType("type1");


        Account account2 = new Account();
        account2.setAccountId(utils.generateAccountId(30));
        account2.setName("account2");
        account2.setType("type2");


        account1.addFarm(farm1);
        account1.addFarm(farm2);
        account2.addFarm(farm3);
        account2.addFarm(farm4);

        account1.setCustomer(customer1);
        account2.setCustomer(customer2);
        customer1.setAccount(account1);
        customer2.setAccount(account2);

        account1.setUserEntity(userEntity);
        account2.setUserEntity(userEntity);
        userEntity.getAccounts().add(account1);
        userEntity.getAccounts().add(account2);

        accountRepository.saveAll(Arrays.asList(account1, account2));

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
