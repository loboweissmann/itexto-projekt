package br.com.itexto.projekt.cli;

/**
 * Exception throwed by the Daft Lisp CLI interface
 * @author kicolobo
 *
 */
public class DaftShellException extends Exception {
	
	private String[] parameters;
	
	public String[] getParameters() {return parameters;}
	
	public DaftShellException(String msg, String[] parameters) {
		super(msg);
		this.parameters = parameters;
	}
	
	public DaftShellException(String msg, Throwable t, String[] parameters) {
		super(msg, t);
		this.parameters = parameters;
	}
	
}
