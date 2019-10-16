package io.bootique;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.vaadin.flow.server.startup.ServletContextListeners;
import io.bootique.jetty.JettyModule;
import io.bootique.utils.Grapher;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.*;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class BootiqueVaadinApp implements Module {
    public static void main(String[] args) throws IOException {
        BQRuntime runtime = Bootique.app("-server", "-config=classpath:config.yml")
            .modules(
                JettyModule.class,
                BootiqueVaadinApp.class
            )
            .createRuntime();
        Grapher.printGraph(runtime);
        runtime.run();
    }

    private WebAppContext buildVaadinWebAppContext() throws Exception {
        //
        // https://vaadin.com/tutorials/embedded-jetty-server-in-vaadin-flow
        //

        URL webRootLocation = JustJettyApplication.class.getResource("/webapp/");
        URI webRootUri = webRootLocation.toURI();

        WebAppContext context = new WebAppContext();
        context.setBaseResource(Resource.newResource(webRootUri));
        context.setContextPath("/");
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*");
        context.setConfigurationDiscovered(true);
        context.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(),
                new WebInfConfiguration(),
                new WebXmlConfiguration(),
                new MetaInfConfiguration()
        });
        context.getServletContext().setExtendedListenerTypes(true);
        context.addEventListener(new ServletContextListeners());

        return context;
    }

    @Override
    public void configure(Binder binder) {
        try {
            JettyModule.extend(binder).addContextHandlerExtender(extender -> {
                try {
                    extender.setHandler(buildVaadinWebAppContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
