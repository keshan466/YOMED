package com.iit.medpredictor;

import com.iit.medpredictor.dto.AuthRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for Auth Service test
 */
public class AuthServiceTest extends AbstractTest
{
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * Test when username and password given will user be authenticated or not
	 */
	@Test
	public void userShouldAuthenticatedWhenUsernameAndPasswordReceived() throws Exception {
		String uri = "/auth/login";
		AuthRequest authRequest = new AuthRequest( "tharu" , "123" , "");
		String inputJson = super.mapToJson(authRequest);
		MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.post(uri)
				.contentType( MediaType.APPLICATION_JSON_VALUE)
				.content( inputJson ))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String response = mvcResult.getResponse().getContentAsString();
		assertTrue( !response.contains( "incorrect" ) );
	}
}
