package br.com.itexto.daftlisp.web;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.itexto.daftlisp.web.PathParser;
import br.com.itexto.daftlisp.web.Request;

public class PathParserTest {

	@Test
	public void testParse() {
		PathParser parser = new PathParser();
		assertNotNull(parser.parse(null));
		
		
		HttpServletRequest mock = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mock.getPathInfo()).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				return "/controller/action";
			}});
		
		Mockito.when(mock.getParameterMap()).thenAnswer(new Answer<Map<String, Object>>() {
			public Map answer(InvocationOnMock invocation) throws Throwable {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("parameter1", "value1");
				map.put("parameter2", "value2");
				return map;
			}
		});
		
		Request request = parser.parse(mock);
		assertNotNull(request);
		assertEquals("Wrong controller", "controller", request.getControllerName());
		assertEquals("Wrong action", "action", request.getActionName());
		assertEquals("value1", request.getParameters().get("parameter1"));
		assertEquals("value2", request.getParameters().get("parameter2"));
		assertNull(request.getParameters().get("something"));
		
	}

}
