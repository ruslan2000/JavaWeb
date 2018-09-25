package by.ruslan.hologram;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to pull images from buffer
 * 
 * 
 * @author Ruslan
 *
 */

public class FileServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String filePath;

	public FileServlet2() {
	}

	public void init() throws ServletException {
		filePath = "/files";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		filePath = request.getParameter("path");
		File file = new File(filePath);
		if (!file.exists()) {
			response.sendError(404);
			return;
		}
		String contentType = getServletContext().getMimeType(file.getName());
		if (contentType == null)
			contentType = "application/octet-stream";
		response.reset();
		response.setBufferSize(10240);
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition",
				(new StringBuilder("attachment; filename=\"")).append(file.getName()).append("\"").toString());
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		input = new BufferedInputStream(new FileInputStream(file), 10240);
		output = new BufferedOutputStream(response.getOutputStream(), 10240);
		byte buffer[] = new byte[10240];
		int length;
		while ((length = input.read(buffer)) > 0)
			output.write(buffer, 0, length);
		close(output);
		close(input);
	}

	private static void close(Closeable resource) {
		if (resource != null)
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
