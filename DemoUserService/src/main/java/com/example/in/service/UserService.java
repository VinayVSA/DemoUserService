package com.example.in.service;

import java.util.List;

import com.example.in.entity.*;



public interface UserService {

	User saveUser(User user);
	
	List<User>getAllUser();
	
	User getUser(int userId);
}
