package com.iit.medpredictor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.iit.medpredictor.entity.type.Medicine;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity class for Order. Used to map orders table in database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
@ToString
@Entity
@Table( name = "orders" )
public class Order
{
	@Id
	@SequenceGenerator(
			name = "order_gen",
			sequenceName = "order_id_seq",
			allocationSize = 1
	)
	@GeneratedValue( generator = "order_gen", strategy = GenerationType.SEQUENCE )
	@EqualsAndHashCode.Include
	private Long orderId;

	@Column( name = "med_type", nullable = false )
	private Medicine medicine;

	@Column( name = "year" )
	private Integer year;

	@Column( name = "month" )
	private Short month;

	@Column( name = "quantity" )
	private Integer quantity;

	@Column( name = "is_predicted")
	private Boolean isPredicted;

	@Column( name = "note" )
	private String note;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "user_id", referencedColumnName = "user_id" )
	@ToString.Exclude
	private User user;
}
