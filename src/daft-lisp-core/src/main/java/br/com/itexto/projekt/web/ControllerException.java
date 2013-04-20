package br.com.itexto.projekt.web;

public class ControllerException extends Throwable {
	
	private Request request;
	
	public Request getRequest() {return this.request;}
	
	public ControllerException(String msg, Request request) {
		super(msg);
		this.request = request;
	}
	
	public ControllerException(String msg, Throwable t, Request request) {
		super(msg, t);
		this.request = request;
	}
	
}
