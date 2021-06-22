package com.iit.medpredictor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Entity class for Order. Used to map orders table in database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
@ToString
@Entity
@Table( name = "users" )
public class User
{
	@Id
	@SequenceGenerator(
			name = "user_gen",
			sequenceName = "user_id_seq",
			allocationSize = 1
	)
	@GeneratedValue( generator = "user_gen", strategy = GenerationType.SEQUENCE )
	@EqualsAndHashCode.Include
	@Column( name = "user_id" )
	private Long userId;

	@Column( name = "username", unique = true, nullable = false )
	private String username;

	@Size( max = 100 )
	@Column( name = "email", unique = true, nullable = false)
	private String email;

	@Column( name = "pw" )
	private String password;

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "user" )
	private Set<Order> orders;
}
