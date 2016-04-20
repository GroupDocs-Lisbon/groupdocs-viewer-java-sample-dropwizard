package com.groupdocs.viewer.samples.dropwizard.views;


import io.dropwizard.views.View;

import java.nio.charset.Charset;

/**
 * The type Viewer view.
 * @author Aleksey Permyakov
 */
public class ViewerView extends View {
    private String contextPath;

    /**
     * Instantiates a new Viewer view.
     * @param contextPath the context path
     */
    public ViewerView(String contextPath) {
        super("viewer.ftl", Charset.forName("UTF-8"));
        this.contextPath = contextPath == null || contextPath.isEmpty() ? "" : contextPath.endsWith("/") ? contextPath : contextPath + "/";
    }

    /**
     * Gets context path.
     * @return the context path
     */
    public String getContextPath() {
        return contextPath;
    }
}
