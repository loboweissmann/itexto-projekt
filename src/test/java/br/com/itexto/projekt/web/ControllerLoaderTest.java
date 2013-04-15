package br.com.itexto.projekt.web;

import java.util.HashMap;
import static org.junit.Assert.*;
import java.util.Map;

import org.junit.Test;

public class ControllerLoaderTest {

	@Test
	public void testExecuteController() throws Throwable {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("param1", "Param 1");
		parameters.put("param2", "Param 2");
		Request request = new Request("test", "action", parameters, null);
		ControllerLoader loader = new ControllerLoader();
		for (int i = 0; i < 4000; i++) {
			Map<String, Object> result = loader.executeController(request);
			assertNotNull(result);
			assertFalse(result.isEmpty());
			assertEquals(parameters.get("param1"), result.get("param1"));
			assertEquals(parameters.get("param2"), result.get("param2"));
		}
		
	}

}
