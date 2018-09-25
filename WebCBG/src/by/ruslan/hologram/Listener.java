package by.ruslan.hologram;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class Listener implements HttpSessionListener {

	public Listener() {
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Session created");
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Session Timed out");
	}
}
