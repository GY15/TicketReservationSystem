package web.listeners;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class CounterListener
 *
 */
@WebListener
public class CounterListener implements HttpSessionListener, ServletContextListener, ServletContextAttributeListener {
	private static int loginCounter=0;
	private static int allCounter=0;
	ServletContextEvent sce;
    /**
     * Default constructor. 
     */
    public CounterListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent cse) {
		sce= cse;
    	ServletContext servletContext= cse.getServletContext();
//		int num[] = readFile();
//		allCounter = num[0];
//		loginCounter = num[1];
		servletContext.setAttribute("loginCounter", Integer.toString(loginCounter));
		servletContext.setAttribute("allCounter", Integer.toString(allCounter));
    	System.out.println("Application initialized");
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0) {
    	System.out.println("ServletContextattribute added");
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scae) {
    	System.out.println("ServletContextattribute replaced");
//    	writeCounter(scae);
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0) {
    	System.out.println("ServletContextattribute removed");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
//		writeFile(0, 0);
    	System.out.println("Application shut down");
    }
	
    synchronized void writeCounter(ServletContextAttributeEvent scae) {
    	ServletContext servletContext= scae.getServletContext();
    	allCounter =  Integer.parseInt((String) servletContext.getAttribute("allCounter"));
		loginCounter = Integer.parseInt((String) servletContext.getAttribute("loginCounter"));
//		writeFile(allCounter, loginCounter);
    }


	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		httpSessionEvent.getSession().setMaxInactiveInterval(60*5);
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		ServletContext servletContext= sce.getServletContext();
		allCounter = Integer.parseInt((String) servletContext.getAttribute("allCounter"));
		loginCounter = Integer.parseInt((String) servletContext.getAttribute("loginCounter"));

		HttpSession session = httpSessionEvent.getSession();
		allCounter--;
		if(session.getAttribute("userID") != null){
			loginCounter--;
		}
		servletContext.setAttribute("loginCounter", Integer.toString(loginCounter));
		servletContext.setAttribute("allCounter", Integer.toString(allCounter));
	}
}
