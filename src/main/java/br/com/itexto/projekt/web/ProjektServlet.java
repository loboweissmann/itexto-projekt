package br.com.itexto.projekt.web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Central access point for the "itexto Projekt Project" :)
 * Servlet implementation class ProjektServlet
 */
public class ProjektServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProjektServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		viewResolver.init(getServletContext(), getServletConfig());
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process("get", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process("post", request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process("put", request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process("delete", request, response);
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 */
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process("trace", request, response);
	}
	
	private final PathParser pathParser = new PathParser();
	private final ControllerLoader controllerLoader = new ControllerLoader();
	private final ViewResolver viewResolver = new ViewResolver();
	
	private void process(String method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Request requestInfo = pathParser.parse(request);
		
		Map<String, Object> model = null;
		try {
			model = controllerLoader.executeController(requestInfo);
		} catch (ControllerException ex) {
			ex.printStackTrace();
		}
		
		viewResolver.resolve(request, response, requestInfo, model);
		/*
		BufferedWriter bw = new BufferedWriter(response.getWriter());
		
		bw.write("controller: " + requestInfo.getControllerName());
		bw.write("\naction: " + requestInfo.getActionName());
		bw.write("\nparameters: " + requestInfo.getParameters());
		bw.write("\nmodel: " + model);
		bw.flush();*/
	}

}
