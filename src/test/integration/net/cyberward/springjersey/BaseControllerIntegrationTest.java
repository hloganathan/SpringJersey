package net.cyberward.springjersey;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class BaseControllerIntegrationTest extends JerseyTest {

	public BaseControllerIntegrationTest(String basePackage) {
		super(
				new WebAppDescriptor.Builder(basePackage)
						.contextPath("jerseyspring")
						.contextParam("contextConfigLocation",
								"net.cyberward.springjersey.config.AppConfig")
						.contextParam("contextClass",
								"org.springframework.web.context.support.AnnotationConfigWebApplicationContext")
						.servletClass(SpringServlet.class)
						.contextListenerClass(ContextLoaderListener.class)
						.requestListenerClass(RequestContextListener.class)
						.build());
	}
}
