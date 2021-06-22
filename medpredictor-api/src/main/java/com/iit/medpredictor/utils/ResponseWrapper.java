package com.iit.medpredictor.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Wrapper class for responses
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseWrapper<T>
{
	private T entity;
	private List<T> entityList;
	private boolean success;
	private String error;
}
