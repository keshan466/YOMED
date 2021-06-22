package com.iit.medpredictor.entity.projection;

import com.iit.medpredictor.entity.type.Medicine;

/**
 * Order interface to use in projections in Order repository.
 */
public interface OrderDTO
{
	Long getOrderId();
	Medicine getMedicine();
	Integer getYear();
	Short getMonth();
	Integer getQuantity();
	Boolean getIsPredicted();
	String getNote();
}
