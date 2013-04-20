package br.com.itexto.projekt.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class Request {
	
	private String controllerName;
	
	public String getControllerName() {return controllerName;}
	
	private String action;
	
	public String getActionName() {return action;}
	
	private Map<String, String> parameters;
	
	public Map<String, String> getParameters() {return parameters;}
	
	public String parameter(String key) {
		return parameters.get(key);
	}
	
	private HttpServletRequest servletRequest;
	
	public Object getSession(String key) {
		return servletRequest.getSession().getAttribute(key);
	}
	
	public void setSession(String key, Object value) {
		servletRequest.getSession(true).setAttribute(key, value);
	}
	
	public Request(String controller, String action, Map<String, String> parameters, HttpServletRequest req) {
		this.action = action;
		this.controllerName = controller;
		this.parameters = parameters;
		this.servletRequest = req;
	}
	
}
