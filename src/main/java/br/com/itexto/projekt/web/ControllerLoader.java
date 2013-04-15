package br.com.itexto.projekt.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clojure.lang.RT;
import clojure.lang.Var;


public class ControllerLoader {
	
	private final Map<String, Object> empty = new HashMap<String, Object>();
	
	private Set<String> loadedControllers = new HashSet<String>();
	
	public Map<String, Object> executeController(Request request) throws ControllerException {
		if (request != null) {
			if (! request.getControllerName().equals("entryPoint")) {
				try {
					if (! loadedControllers.contains(request.getControllerName())) {
						RT.loadResourceScript("br/com/itexto/projekt/controllers/" + request.getControllerName() +".clj");
						loadedControllers.add(request.getControllerName());
					}
				} catch (IOException e) {
					throw new ControllerException("Error loading controller", e, request);
				}
				
				Var action = RT.var(request.getControllerName(), request.getActionName());
				try {
					return (Map<String, Object>) action.invoke(request);
				} catch (Throwable t) {
					throw new ControllerException("Error executing action", t, request);
				}
				
			}
		}
		return empty;
	}

}
