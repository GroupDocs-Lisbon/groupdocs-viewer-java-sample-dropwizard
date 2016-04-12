package com.groupdocs.viewer.samples.dropwizard;

import com.groupdocs.viewer.samples.dropwizard.config.DropwizardConfig;
import com.groupdocs.viewer.samples.dropwizard.config.ViewerConfig;
import com.groupdocs.viewer.samples.dropwizard.health.TemplateHealthCheck;
import com.groupdocs.viewer.samples.dropwizard.resources.ViewerResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.MultipartConfigElement;

/**
 *
 * @author Aleksey Permyakov
 */

public class MainService extends Application<DropwizardConfig> {
    
    public static void main( String[] args ) throws Exception{
        new MainService().run(args);
    }

    @Override
    public String getName() {
        return "GroupDocs Viewer for Java v3.0.0";
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfig> bootstrap) {
        bootstrap.addBundle(new MultiPartBundle());
        bootstrap.addBundle(new ViewBundle<DropwizardConfig>());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/"));
    }

    @Override
    public void run(DropwizardConfig configuration, Environment environment) throws Exception {
        final ViewerResource resource = new ViewerResource(new ViewerConfig(configuration), configuration);
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck("template");
        environment.healthChecks().register("template", healthCheck);

        ServletHolder mainHolder = new ServletHolder(environment.getJerseyServletContainer());
        mainHolder.getRegistration().setMultipartConfig(new MultipartConfigElement("data/tmp", 1048576, 1048576, 262144));


        environment.jersey().register(resource);
    }
}
