package com.iit.medpredictor;

import com.iit.medpredictor.entity.Order;
import com.iit.medpredictor.entity.type.Medicine;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for order service class
 */
public class OrderServiceTest extends AbstractTest
{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * Test when requested will latest 50 orders returns
	 */
	@Test
	public void latestOrdersListShouldReturnWhenOrdersHasRequested() throws Exception {
		String uri = "/orders";
		MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	/**
	 * Test when requested will order creates
	 */
	@Test
	public void orderShouldAddWhenOrderCreatedRequested() throws Exception {
		String uri = "/orders?uid=1";
		Order order = new Order( 0L, Medicine.ASPIRIN, 2001, (short)1, 500, true, "test note", null );
		String inputJson = super.mapToJson(order);
		MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content( inputJson ))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}


}
