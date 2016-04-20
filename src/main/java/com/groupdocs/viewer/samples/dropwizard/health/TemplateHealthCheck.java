package com.groupdocs.viewer.samples.dropwizard.health;


import com.codahale.metrics.health.HealthCheck;

/**
 * Dummy HealthCheck
 * @author Aleksey Permyakov
 */
public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    /**
     * Instantiates a new Template health check.
     * @param template the template
     */
    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    /**
     * Check result.
     * @return the result
     * @throws Exception the exception
     */
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

}
