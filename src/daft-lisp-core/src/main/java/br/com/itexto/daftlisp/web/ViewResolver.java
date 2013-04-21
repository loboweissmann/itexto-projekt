package br.com.itexto.daftlisp.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class ViewResolver {
	
	
	
	private ServletContext servletContext;
	private ServletConfig  servletConfig;
	
	public void init(ServletContext servletContext, ServletConfig servletConfig) {
		this.servletContext = servletContext;
		this.servletConfig  = servletConfig;
	}
	
	public void resolve(HttpServletRequest request, HttpServletResponse response, Request infoRequest, Map<String, Object> model) throws ParseErrorException, MethodInvocationException, ResourceNotFoundException, IOException {
		VelocityContext velocityContext = new VelocityContext();
		for (String key : model.keySet()) {
			velocityContext.put(key, model.get(key));
		}
		response.setContentType("text/html");
		Reader templateReader = new InputStreamReader(servletContext.getResourceAsStream("/views/" + infoRequest.getControllerName() + "/" + infoRequest.getActionName() + ".jsp"));
		Velocity.evaluate(velocityContext, response.getWriter(), "", templateReader);
		//.evaluate(velocityContext, response.getOutputStream(), infoRequest.getActionName(), templateReader);
		
		
		
	}
	
	
	
}
