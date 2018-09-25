package by.ruslan.hologram;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ruslan.hologram.commands.CommandSelector;
import by.ruslan.hologram.commands.ICommand;
import by.ruslan.hologram.utill.ParamsHelper;

public class MyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void init() throws ServletException {
		
		String webAppPath = System.getProperty("user.dir").concat("\\WebContent\\");
		System.setProperty("rootPath", webAppPath);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ParamsHelper ph = new ParamsHelper(request);
		if (ph.getParameter("logOut") != null) {
			ph.closeSession();
			response.sendRedirect(
					(new StringBuilder(String.valueOf(request.getContextPath()))).append("/index.jsp").toString());
		} else {
			CommandSelector commandSelector = CommandSelector.getInstance();
			ICommand currentCommand = commandSelector.getCommand(ph);
			String page = currentCommand.execute(ph);
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	public void destroy() {
		super.destroy();
	}

}
