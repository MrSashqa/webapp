package project.controller.wrapper;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestWrapper {
    private HttpServletRequest request;
    private HttpSession httpSession;

    public RequestWrapper(HttpServletRequest request) {
        this.request = request;
        this.httpSession = request.getSession();
    }


    public HttpServletRequest getRequest() {

        return request;
    }

    public String getRequestParameter(String param) {

        return request.getParameter(param);
    }

    public void setAttribute(String key, Object value) {

        request.setAttribute(key, value);
    }

    public void setAttributeToSession(String key, Object value) {

        httpSession.setAttribute(key, value);
    }

    public Object getAttributeFromSession(String key) {

        return httpSession.getAttribute(key);
    }

    public String[] getParameterValues(String name) {

        return request.getParameterValues(name);
    }


}
