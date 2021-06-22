package com.iit.medpredictor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Request object for login and registration
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRequest
{
	private String username;
	private String password;
	private String email;
}
