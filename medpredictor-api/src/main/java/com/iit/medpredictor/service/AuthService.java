package com.iit.medpredictor.service;

import com.iit.medpredictor.dto.AuthRequest;
import com.iit.medpredictor.dto.AuthResponse;
import com.iit.medpredictor.entity.User;
import com.iit.medpredictor.repository.UserRepository;
import com.iit.medpredictor.utils.AbstractService;
import com.iit.medpredictor.utils.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for user authentication related functions.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService extends AbstractService
{

	final UserRepository userRepository;

	/**
	 * User login implementation
	 */
	public ResponseEntity< ResponseWrapper > loginUser( AuthRequest authRequest )
	{
		ResponseEntity< ResponseWrapper > response = null;

		Optional<User> optionalUser = userRepository.findUserByUsernameAndAndPassword( authRequest.getUsername(), authRequest.getPassword() );

		if( optionalUser.isPresent() )
		{
			User tempUser = optionalUser.get();
			response = buildEntityResponse( new AuthResponse(tempUser.getUserId(), tempUser.getUsername()) , HttpStatus.OK );;
		}
		else
		{
			response = buildErrorResponse( "The username or password is incorrect or user may not registered yet", HttpStatus.OK );
		}

		return response;
	}

	/**
	 * User registration implementation
	 */
	public ResponseEntity< ResponseWrapper > registerUser( AuthRequest authRequest )
	{
		ResponseEntity<ResponseWrapper> response = null;



		if ( !userRepository.existsByUsername( authRequest.getUsername() ) && !userRepository.existsByEmail( authRequest.getEmail() ))
		{
			User user = new User(0L , authRequest.getUsername(), authRequest.getEmail(), authRequest.getPassword(), null);
			User tempUser = userRepository.save( user );
			response = buildEntityResponse( new AuthResponse(tempUser.getUserId(), tempUser.getUsername()) , HttpStatus.CREATED );;

		}
		else
			{
			response = buildErrorResponse( "Your username or email has reserved by someone before. Please use another.", HttpStatus.OK );
		}

		return response;
	}


}
