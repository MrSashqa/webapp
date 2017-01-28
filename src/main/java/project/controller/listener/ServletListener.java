package project.controller.listener;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.InputStream;


@WebListener("log4j initializer")
public class ServletListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PropertyConfigurator.configure(servletContextEvent.getServletContext().getRealPath("/WEB-INF/classes/log4j.properties"));
        LOGGER.info("Log4j has been initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
                /*empty*/
    }

}
