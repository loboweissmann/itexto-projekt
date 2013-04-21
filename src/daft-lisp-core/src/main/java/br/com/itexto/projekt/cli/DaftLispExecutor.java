package br.com.itexto.projekt.cli;

import java.io.File;
import java.io.IOException;

import clojure.lang.RT;
import clojure.lang.Var;
import clojure.pprint.cl_format__init;

/**
 * Executa os comandos do Shell
 * @author kicolobo
 */
public class DaftLispExecutor {
	
	public boolean loadingScriptsFromClasspath() {
		return System.getenv("DAFT_LISP_HOME") == null && 
			   System.getProperty("DAFT_LISP_HOME") == null;
	}
	
	public String getDaftLispHome() {
		if (! loadingScriptsFromClasspath()) {
			return System.getenv("DAFT_LISP_HOME") != null ? System.getenv("DAFT_LISP_HOME") : System.getProperty("DAFT_LISP_HOME");
		} else {
			return null;
		}
	}
	
	private String getScriptInputStream(String name) throws DaftShellException {
		if (name != null) {
			if (loadingScriptsFromClasspath()) {
				return "daftlisp/scripts/" + name + ".clj";
			} else {
				File file = new File(getDaftLispHome() + File.separator + "scripts" + File.separator + name + ".clj");
				return file.getAbsolutePath();
			}
		}
		return null;
	}
	
	public Object execute(String[] parameters) throws DaftShellException {
		if (parameters == null || parameters.length == 0) {
			throw new DaftShellException("No parameters sent to DaftLispExecutor", parameters);
		}
		
		String script = getScriptInputStream(parameters[0]);
		try {
			if (loadingScriptsFromClasspath()) {
				RT.loadResourceScript(script);
			} else {
				
				clojure.lang.Compiler.loadFile(script);
				
			}
		} catch (IOException e) {
			throw new DaftShellException("Error loading script: " + script, e, parameters);
		}
		
		Var scriptCode = RT.var(parameters[0], "script");
		String[] parameters_script = null;
		if (parameters.length > 1) {
			parameters_script = new String[parameters.length - 1];
			for (int i = 1; i < parameters.length; i++) {
				parameters_script[i-1] = parameters[i];
			}
		} 
		return scriptCode.invoke(parameters_script);
	}
	
}
