package com.example.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.in.entity.*;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
