package com.reiterweg.ytg.service;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Ping extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json");
		resp.getWriter().println("{ \"alive\" : true }");
	}
}
