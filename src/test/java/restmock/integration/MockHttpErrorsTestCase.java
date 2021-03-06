package restmock.integration;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.ContentExchange;
import org.junit.Test;

import restmock.RestMock;
import restmock.request.HttpMethod;

public class MockHttpErrorsTestCase extends IntegrationTestBase {
	
	@Test
	public void returningError404() throws Exception {
		RestMock.whenGet("/test").thenReturnText("Hello World!");
		
		ContentExchange exchange = sendRequestAndWaitForDone(baseUrl + "/test1", HttpMethod.GET);
		
		assertEquals(HttpServletResponse.SC_NOT_FOUND, exchange.getResponseStatus());
	}
	
	@Test
	public void returningBadRequestForGETMethod() throws Exception {
		RestMock.whenGet("/test").thenReturnErrorCodeWithMessage(HttpServletResponse.SC_BAD_REQUEST, "Message for error 500 GET");
		
		ContentExchange exchange = sendRequestAndWaitForDone(baseUrl + "/test", HttpMethod.GET);
		
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, exchange.getResponseStatus());
		assertEquals("Message for error 500 GET" + lineSeparator(), exchange.getResponseContent());
	}
	
	@Test
	public void returningBadRequestForPOSTMethod() throws Exception {
		RestMock.whenPost("/test").thenReturnErrorCodeWithMessage(HttpServletResponse.SC_BAD_REQUEST, "Message for error 500 POST");
		
		ContentExchange exchange = sendRequestAndWaitForDone(baseUrl + "/test", HttpMethod.POST);
		
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, exchange.getResponseStatus());
		assertEquals("Message for error 500 POST" + lineSeparator(), exchange.getResponseContent());
	}
	
	@Test
	public void returningForbiddenForGETMethod() throws Exception {
		RestMock.whenGet("/test").thenReturnErrorCodeWithMessage(HttpServletResponse.SC_FORBIDDEN, "Forbidden GET");
		
		ContentExchange exchange = sendRequestAndWaitForDone(baseUrl + "/test", HttpMethod.GET);
		
		assertEquals(HttpServletResponse.SC_FORBIDDEN, exchange.getResponseStatus());
		assertEquals("Forbidden GET" + lineSeparator(), exchange.getResponseContent());
	}
	
	@Test
	public void returningForbiddenForPOSTMethod() throws Exception {
		RestMock.whenPost("/test").thenReturnErrorCodeWithMessage(HttpServletResponse.SC_FORBIDDEN, "Forbidden POST");
		
		ContentExchange exchange = sendRequestAndWaitForDone(baseUrl + "/test", HttpMethod.POST);
		
		assertEquals(HttpServletResponse.SC_FORBIDDEN, exchange.getResponseStatus());
		assertEquals("Forbidden POST" + lineSeparator(), exchange.getResponseContent());
	}
	@Test
	public void testPrefix()throws Exception{
		RestMock.prefix("/prefix").whenGet("ptest").thenReturnText("Hello");
		ContentExchange exchange = sendRequestAndWaitForDone(baseUrl + "/prefix/ptest", HttpMethod.GET);
		assertEquals(HttpServletResponse.SC_OK, exchange.getResponseStatus());
		System.out.println(exchange.getResponseContent());
	}

}
