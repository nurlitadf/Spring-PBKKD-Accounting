package com.pbkk.accounting.restapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbkk.accounting.exception.ResourceNotFoundException;
import com.pbkk.accounting.model.Restaurant;
import com.pbkk.accounting.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@ResponseBody
	@RequestMapping("")
	public List<Restaurant> getRestaurant(){
		return restaurantRepository.findAll();
	}
	@ResponseBody
	@RequestMapping("/{id}")
	public Restaurant getRestaurantById(@PathVariable(value = "id") Long id){
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
		return optionalRestaurant.get();
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Restaurant createRestaurant(@RequestParam(value="restaurant_id") Long restaurant_id, 
			@RequestParam(value="name") String name, @RequestParam(value="address")String address){		
		Restaurant restaurant=new Restaurant();
		restaurant.setRestaurant_id(restaurant_id);
		restaurant.setRestaurant_name(name);
		restaurant.setRestaurant_address(address);
		return restaurantRepository.save(restaurant);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Restaurant updateCustomer(@PathVariable(value = "id") Long id, 
			@RequestParam(value="name") String name, @RequestParam(value="address")String address) {
		Restaurant restaurant = restaurantRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));
		if(name!=null&&name!="") restaurant.setRestaurant_name(name);;
		restaurant.setRestaurant_address(address);
	    return restaurantRepository.save(restaurant);
	}
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long id) {
	    Restaurant restaurant = restaurantRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));

	    restaurantRepository.delete(restaurant);

	    return ResponseEntity.ok().build();
	}
}
