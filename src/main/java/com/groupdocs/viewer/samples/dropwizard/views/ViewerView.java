package com.groupdocs.viewer.samples.dropwizard.views;


import io.dropwizard.views.View;

import java.nio.charset.Charset;

/**
 *
 * @author Aleksey Permyakov
 */
public class ViewerView extends View {
    private String contextPath;

    public ViewerView(String contextPath) {
        super("viewer.ftl", Charset.forName("UTF-8"));
        this.contextPath = contextPath == null || contextPath.isEmpty() ? "" : contextPath.endsWith("/") ? contextPath : contextPath + "/";
    }

    public String getContextPath() {
        return contextPath;
    }
}
