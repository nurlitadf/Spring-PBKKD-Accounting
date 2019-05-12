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
import com.pbkk.accounting.model.Menu;
import com.pbkk.accounting.model.Restaurant;
import com.pbkk.accounting.repository.MenuRepository;
import com.pbkk.accounting.repository.RestaurantRepository;

@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
    private MenuRepository menuRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@ResponseBody
	@RequestMapping("")
	public List<Menu> getMenu(){
		return menuRepository.findAll();
	}
	@ResponseBody
	@RequestMapping("/{id}")
	public Menu getMenuById(@PathVariable(value = "id") Long id){
		Optional<Menu> optionalMenu = menuRepository.findById(id);
		return optionalMenu.get();
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Menu createMenu(@RequestParam(value="menu_id") Long menu_id, @RequestParam(value="restaurant_id")Long restaurant_id,
			@RequestParam(value="name") String name, @RequestParam(value="price")int price){
		
		Menu menu=new Menu();
		Restaurant restaurant = restaurantRepository.findById(restaurant_id)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurant_id));
		menu.setMenuId(menu_id);
		menu.setRestaurant(restaurant);
		menu.setMenuName(name);
		menu.setMenuPrice(price);
		return menuRepository.save(menu);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Menu updateCustomer(@PathVariable(value = "id") Long id, @RequestParam(value="restaurant_id")Long restaurant_id,
			@RequestParam(value="name") String name, @RequestParam(value="price")int price) {
		Menu menu = menuRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", id));
		if(name!=null&&name!="") menu.setMenuName(name);;
		menu.setMenuPrice(price);
		Restaurant restaurant = restaurantRepository.findById(restaurant_id)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurant_id));
		menu.setRestaurant(restaurant);
	    return menuRepository.save(menu);
	}
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long id) {
	    Menu menu = menuRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", id));

	    menuRepository.delete(menu);

	    return ResponseEntity.ok().build();
	}

}
