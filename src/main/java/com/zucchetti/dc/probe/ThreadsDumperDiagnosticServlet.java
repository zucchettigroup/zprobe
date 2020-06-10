package com.zucchetti.dc.probe;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zucchetti.dc.zprobe.core.ThreadsDumperDiagnostic;

/**
 * Servlet implementation class ThreadsDumperDiagnosticServlet
 */
public class ThreadsDumperDiagnosticServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Date dumpTime = new Date();
		String dumpStr;
		try
		{
			dumpStr = ThreadsDumperDiagnostic.threadPrint();
		} 
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

		response.getWriter()
		.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
		.append("<html>")
		.append("<head>")
		.append("<meta http-equiv=\"Content-Type\" content=\"text/html; UTF-8\">")
		.append("<title>Dump Threads Diagnostic</title>")
		.append("</head>")
		.append("<body style=\"text-align: center; margin: 20px\">")
		.append("<p>Dump time ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SS").format(dumpTime)).append("</p>")
		.append("<textarea class=\"dumpTextArea\" readonly=\"readonly\" style=\"display: block; font-size: 12px\" rows=\"50\" cols=\"180\">").append(dumpStr).append("</textarea>")
		.append("</body>")
		.append("</html>");

	}
}
