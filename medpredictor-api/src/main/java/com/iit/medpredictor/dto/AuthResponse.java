package com.iit.medpredictor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Response object for login and registration requests
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthResponse
{
	private Long userId;
	private String username;
}
