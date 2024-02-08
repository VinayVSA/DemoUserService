package com.example.in.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.in.entity.Hotel;
import com.example.in.entity.Rating;
import com.example.in.entity.User;
import com.example.in.externalservice.HotelService;
import com.example.in.externalservice.RatingService;
import com.example.in.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
	UserRepository userrepo;
    
	@Autowired
    RestTemplate resttemplate;
	
	@Autowired
	HotelService hotelservice;
	
    @Autowired
	RatingService ratingservice;
    
	@Override
	public User saveUser(User user) {
		
		return userrepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
	
		return userrepo.findAll();
	}

	@Override
	public User getUser(int userId) {
		
		
     User user = userrepo.findById(userId).orElseThrow(() -> new ArithmeticException("User with given id is not found on serve"));
		        	
    
    Rating[] ratingsOfUser = resttemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		        
		        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		        List<Rating> ratingList = ratings.stream().map(rating -> {
		            
		            Hotel hotel = hotelservice.getHotel(rating.getHotelId());
		           
		            rating.setHotel(hotel);
		           
		            return rating;
		        }).collect(Collectors.toList());

		        user.setRating(ratingList);

		        return user;
	}

}
