package com.iit.medpredictor.service;

import com.iit.medpredictor.entity.Order;
import com.iit.medpredictor.entity.User;
import com.iit.medpredictor.entity.type.Medicine;
import com.iit.medpredictor.repository.OrderRepository;
import com.iit.medpredictor.repository.UserRepository;
import com.iit.medpredictor.utils.AbstractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Service class for initial data handling functions
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InitialService extends AbstractService
{
	final UserRepository userRepository;

	final OrderRepository orderRepository;

	/**
	 * Add initial admin user and history of orders to the database implementation
	 */
	public boolean addInitialData()
	{
		boolean isSucceed = false;

		try
		{
			User savedUser;

			if( !userRepository.existsByUsername( "admin" ) )
			{
				User admin = new User( 0L , "admin", "admin@gmail.com", "admin", null);
				savedUser = userRepository.save( admin );
			}
			else
			{
				savedUser = userRepository.findUserByUsername( "admin" );
			}


			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/test.csv"));
			String line;
			Order order = new Order();
			while (( line = br.readLine()) != null)
			{

				String[] values = line.split(",");
				order.setOrderId( 0L );
				order.setMedicine( Medicine.values()[Integer.parseInt( values[0] )] );
				order.setYear( Integer.parseInt( values[1] ) );
				order.setMonth( Short.parseShort( values[2] ) );
				order.setIsPredicted( false );
				order.setQuantity( Integer.parseInt( values[3] ) );
				order.setUser( savedUser );
				order.setNote( null );

				orderRepository.save( order );

			}


			isSucceed = true;

		}
		catch( Exception ex )
		{
			ex.printStackTrace();
		}

		return isSucceed;

	}

	/**
	 * Delete initial data added implementation
	 */
	public void deleteInitialData(Long id){
		orderRepository.deleteById( id );
	}
}
