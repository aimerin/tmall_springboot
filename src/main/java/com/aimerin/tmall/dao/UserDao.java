package com.aimerin.tmall.dao;

import com.aimerin.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByName(String name);

    User getByNameAndPassword(String name, String password);
}
