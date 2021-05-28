package com.dj.webservices.myrestwebservice.repository;

import com.dj.webservices.myrestwebservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
