package com.iit.medpredictor.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Abstract class for services
 */
public abstract class AbstractService
{
	ResponseWrapper responseWrapper;

	/**
	 * Create response entity for responses with single object.
	 */
	public ResponseEntity< ResponseWrapper > buildEntityResponse( Object entity, HttpStatus httpStatus ){
		responseWrapper = new ResponseWrapper( entity, null, true, null );
		return ResponseEntity.status( httpStatus ).body( responseWrapper );
	}

	/**
	 * Create response entity for responses with list of objects.
	 */
	public ResponseEntity< ResponseWrapper > buildEntityListResponse( List<Object> entityList, HttpStatus httpStatus){
		responseWrapper = new ResponseWrapper( null, entityList, true, null );
		return ResponseEntity.status( httpStatus ).body( responseWrapper );
	}

	/**
	 * Create response entity for responses with errors.
	 */
	public ResponseEntity< ResponseWrapper > buildErrorResponse( String message, HttpStatus httpStatus ){
		responseWrapper = new ResponseWrapper( null, null, false, message );
		return ResponseEntity.status( httpStatus ).body( responseWrapper );
	}


}
