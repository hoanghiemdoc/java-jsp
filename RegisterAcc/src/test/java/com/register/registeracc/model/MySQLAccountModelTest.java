package com.register.registeracc.model;

import com.register.registeracc.entity.Account;
import com.register.registeracc.entity.myenum.AccountStatus;
import com.register.registeracc.util.SHA512Hasher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MySQLAccountModelTest {
    private MySQLAccountModel model;
    @BeforeEach
    void setUp() {
        model = new MySQLAccountModel();
    }

    @Test
    void save() {
        Account account = new Account();
        String salt = SHA512Hasher.randomString(10);
        account.setSalt(salt);
        account.setUsername("ngochithanhdat");
        account.setPassword("ngodat02");
        account.setPasswordHash(SHA512Hasher.encode(account.getPassword(), salt));
        account.setPhone("0392847328");
        account.setEmail("ngodat02@gmail.com");
        account.setStatus(AccountStatus.ACTIVE);
        System.out.println(account.toString());
        model.save(account);
    }

    @Test
    void testLogin() {
        String username = "ngochithanhdat";
        String passwordUserEnter = "ngodat02";
        // Tìm tài khoản theo username
        Account account = model.findByUsername(username);
        if(account == null){
            System.err.println("Invalid information");
        }else{
            String passwordHash = account.getPasswordHash();
            String salt = account.getSalt();
            String hash = SHA512Hasher.encode(passwordUserEnter, salt);
            System.out.println(hash);
            System.out.println(passwordHash);
            if(passwordHash.equals(hash)){
                System.out.println("Login success!");
            }
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}