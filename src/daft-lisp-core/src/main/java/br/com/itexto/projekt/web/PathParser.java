package br.com.itexto.projekt.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Responsavel por parsear o path acessado para um controlador especifico
 * @author kicolobo
 *
 */
public class PathParser {
	
	private final Request entryPoint = new Request("entryPoint", "entryPoint", new HashMap<String, String>(), null);
	
	public Request parse(HttpServletRequest request) {
		if (request != null) {
			String path = request.getPathInfo();
			
			String[] components = path.startsWith("/") ? path.substring(1).split("/") : path.split("/");
			
			if (components.length > 0) {
				String controllerName = components[0];
				String actionName = components.length > 1 ? components[1] : "index";
				Map<String, String> parameters = new HashMap<String, String>();
				for (Object key : request.getParameterMap().keySet()) {
					parameters.put(key.toString(), request.getParameter(key.toString()));
				}
				
				return new Request(controllerName, actionName, parameters, request);
			}
			
		}
		return entryPoint;
	}

}
