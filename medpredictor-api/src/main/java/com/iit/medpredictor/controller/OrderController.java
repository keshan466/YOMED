package com.iit.medpredictor.controller;

import com.iit.medpredictor.entity.Order;
import com.iit.medpredictor.service.OrderService;
import com.iit.medpredictor.utils.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for order related functions
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController
{
	final OrderService orderService;

	@GetMapping(value = "test")
	public String getTest()
	{
		return "test string";
	}

	/**
	 * Get latest 50 orders made by the users.
	 */
	@GetMapping
	public ResponseEntity< ResponseWrapper > getLast50Orders()
	{
		return orderService.getLast50Orders();
	}

	/**
	 * Create an order by the user.
	 */
	@PostMapping
	public ResponseEntity< ResponseWrapper > createOrder( @RequestBody Order order, @RequestParam("uid") Long userId )
	{
		return orderService.createOrder( order, userId );
	}

}
