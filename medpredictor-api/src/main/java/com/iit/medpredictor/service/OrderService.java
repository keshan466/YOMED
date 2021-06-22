package com.iit.medpredictor.service;

import com.iit.medpredictor.entity.Order;
import com.iit.medpredictor.entity.User;
import com.iit.medpredictor.entity.type.Medicine;
import com.iit.medpredictor.repository.OrderRepository;
import com.iit.medpredictor.repository.UserRepository;
import com.iit.medpredictor.utils.AbstractService;
import com.iit.medpredictor.utils.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for order related functions
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService extends AbstractService
{
	final OrderRepository orderRepository;

	final UserRepository userRepository;

	/**
	 * Create an order by the user implementation
	 */
	public ResponseEntity< ResponseWrapper > createOrder( Order order, Long userId )
	{
		ResponseEntity< ResponseWrapper > response = null;

		Optional<User> optionalUser = userRepository.findById( userId );

		if( optionalUser.isPresent() )
		{
			User tempUser = optionalUser.get();
			order.setUser( tempUser );

			try
			{
				Order savedOrder = orderRepository.save( order );
				response = buildEntityResponse( savedOrder, HttpStatus.CREATED );
			}
			catch( Exception ex )
			{
				response = buildErrorResponse( "Order saving error : " + ex.toString() , HttpStatus.INTERNAL_SERVER_ERROR );
			}

		}
		else
		{
			response = buildErrorResponse( "Unauthorized user has attempted to do a forbidden activity." , HttpStatus.UNAUTHORIZED);
		}

		return response;
	}

	/**
	 * Get latest 50 orders made by the users implementation
	 */
	public ResponseEntity< ResponseWrapper > getLast50Orders( )
	{
		ResponseEntity< ResponseWrapper > response = null;

		try{

			response = buildEntityListResponse( orderRepository.findLatestOrders().stream().map( i -> (Object) i ).collect( Collectors.toList()), HttpStatus.OK );

		}
		catch( Exception ex )
		{
			response = buildErrorResponse( "Order retrieving error : " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
}
