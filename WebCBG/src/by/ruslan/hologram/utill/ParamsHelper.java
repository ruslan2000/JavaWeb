package by.ruslan.hologram.utill;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ParamsHelper {
	
	private Map requestMap;
	private Map sessionMap;
	private HttpServletRequest request;
	

	public ParamsHelper(HttpServletRequest request) {
		this.request = request;
		requestMap = requestToParameterHelper(request);
		sessionMap = sessionToParameterHelper(request);
	}

	public String getParameter(String parameter) {
		String param = null;
		if (requestMap != null) {
			for (Iterator it = requestMap.entrySet().iterator(); it.hasNext();) {
				java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
				if (parameter.equals(entry.getKey()))
					param = entry.getValue().toString();
			}

		}
		return param;
	}

	public String[] getParameterValues(String parameter) {
		return request.getParameterValues(parameter);
	}

	public void setAttributeToRequest(String attributeName, Object attribute) {
		if (request != null)
			request.setAttribute(attributeName, attribute);
	}

	public Object getAttribute(String attributeName) {
		Object attribute = null;
		if (sessionMap != null) {
			for (Iterator it = sessionMap.entrySet().iterator(); it.hasNext();) {
				java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
				if (attributeName.equals(entry.getKey()))
					attribute = entry.getValue();
			}

		}
		return attribute;
	}

	public void setAttributeToSession(String attributeName, Object attribute) {
		if (request != null)
			request.getSession().setAttribute(attributeName, attribute);
	}

	public void closeSession() {
		if (request != null)
			request.getSession().invalidate();
	}

	private Map sessionToParameterHelper(HttpServletRequest request) {
		Enumeration attributes = request.getSession().getAttributeNames();
		sessionMap = new HashMap();
		String next;
		for (; attributes.hasMoreElements(); sessionMap.put(next, request.getSession().getAttribute(next)))
			next = (String) attributes.nextElement();

		return sessionMap;
	}

	private Map requestToParameterHelper(HttpServletRequest request) {
		Map requestMap = new HashMap();
		java.util.Map.Entry entry;
		for (Iterator it = request.getParameterMap().entrySet().iterator(); it.hasNext(); requestMap
				.put((String) entry.getKey(), ((String[]) entry.getValue())[0]))
			entry = (java.util.Map.Entry) it.next();

		return requestMap;
	}
}
