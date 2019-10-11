package io.bootique;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.jetty.JettyModule;
import io.bootique.jetty.server.ServletContextHandlerExtender;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

public class BootiqueVaadinApp implements Module {
    public static void main(String[] args) {
        BQRuntime runtime = Bootique.app("-server", "-config=classpath:config.yml")
            .modules(
                JettyModule.class,
                BootiqueVaadinApp.class
            )
            .createRuntime();
        runtime.run();
    }

    @Override
    public void configure(Binder binder) {
        WebAppContext webAppContext = new WebAppContext();
        //http://qaru.site/questions/164726/spring-31-webapplicationinitializer-embedded-jetty-8-annotationconfiguration
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setContextPath("/");
        webAppContext.setConfigurations(new Configuration[] { new AnnotationConfiguration(), new WebInfConfiguration() });
        webAppContext.setParentLoaderPriority(true);
        ServletContextHandler servletContextHandler = new ServletContextHandler();


    }
}
