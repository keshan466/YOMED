package com.iit.medpredictor.repository;

import com.iit.medpredictor.entity.Order;
import com.iit.medpredictor.entity.projection.OrderDTO;
import com.iit.medpredictor.entity.type.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for order entity
 */
public interface OrderRepository extends JpaRepository< Order, Long >
{
	/**
	 * Query method to get latest orders
	 */
	@Query( value = "select o.order_id as orderId, o.med_type as medicine, o.year as year, o.month as month, o.quantity as quantity, o.is_predicted as isPredicted, o.note as note" +
			" from orders o " +
			"order by o.year desc, o.month desc fetch first 50 rows only", nativeQuery = true )
	List< OrderDTO > findLatestOrders();

	/**
	 * Query method to count all orders
	 */
	@Query(value = "select count(o) from Order o")
	Long countAll();
}
