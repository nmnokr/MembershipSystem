package LISTENER;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import PROPERTIES.PropertiesService;
import SERVICE.ServiceFacede;

public class ServletContextListenerImpl implements ServletContextListener {
	final Logger logger = Logger.getLogger(ServletContextListenerImpl.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		logger.debug("contextInitialized metodu çalışmaya başladı.");
		Properties appProperties = PropertiesService.getProperties();
		try {
			ServiceFacede.getInstance().initialize(appProperties);
		} catch (Exception e) {
			logger.error("ServletContextListenerImpl contextInitialized metodu exeption = " + e);
		}

		logger.info("contextInitialized metodu çalışması bitti.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}