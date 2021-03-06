package com.zucchetti.dc.probe;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zucchetti.dc.zprobe.core.ThreadsDumper;
import com.zucchetti.dc.zprobe.core.ThreadsDumper.ThreadsDumperConf;

/**
 * Servlet implementation class ThreadDump
 */
public class ThreadsDumperServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		StringWriter sw = new StringWriter();
		ThreadsDumper.dump(sw, ThreadsDumperConf.builder()
				.maxFramesSize()
				.lockedMonitors(true)
				.lockedSynchronizers(true)
				.threadsFilter(ThreadsDumper.NO_FILTER)
				.build());
		Date dumpTime = new Date();
		
		response.getWriter()
			.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
			.append("<html>")
				.append("<head>")
					.append("<meta http-equiv=\"Content-Type\" content=\"text/html; UTF-8\">")
					.append("<title>Dump Threads</title>")
				.append("</head>")
				.append("<body style=\"text-align: center; margin: 20px\">")
					.append("<img alt=\"thread dump\" src=\"thread-dump-analyzer.png\" style=\"width:150px;height:100px;\">")
					.append("<p>Dump time ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SS").format(dumpTime)).append("</p>")
					.append("<textarea class=\"dumpTextArea\" readonly=\"readonly\" style=\"display: block; font-size: 12px\" rows=\"40\" cols=\"180\">").append(sw.toString()).append("</textarea>")
					.append("<br/><p><a href=\"index.html\"><img border=\"0\" alt=\"home\" src=\"home.png\"></a></p>")
				.append("</body>")
			.append("</html>");
	}
}