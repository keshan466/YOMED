package com.iit.medpredictor.controller;

import com.iit.medpredictor.service.InitialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for initial data handling functions
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/home")
public class InitialController
{
	final InitialService initialService;

	/**
	 * Add initial admin user and history of orders to the database
	 */
	@GetMapping
	public boolean addInitialData(){
		return initialService.addInitialData();
	}

	/**
	 * Delete initial data added
	 */
	@DeleteMapping("/{id}")
	public void deleteInitialData( @PathVariable("id") Long orderId ){
		initialService.deleteInitialData( orderId );
	}
}
