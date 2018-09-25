package by.ruslan.hologram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ruslan.hologram.utill.PropertiesManager;

public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetUserServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName").trim();
		if (userName == null || "".equals(userName))
			userName = "Guest";
		String greetings = (new StringBuilder("Hello ")).append(userName).toString();
		response.setContentType("text/plain");
		response.getWriter().write(greetings);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		String path = PropertiesManager.getProperty("loaderGif");
		byte imageBytes[] = getImageAsBytes(path);
		response.setContentType("image/gif");
		response.setContentLength(imageBytes.length);
		try {
			response.getOutputStream().write(imageBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] getImageAsBytes(String path) {
		Path pathToFile = Paths.get(path, new String[0]);
		byte data[] = null;
		try {
			data = Files.readAllBytes(pathToFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
