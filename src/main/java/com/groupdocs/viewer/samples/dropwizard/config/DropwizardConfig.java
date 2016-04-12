package com.groupdocs.viewer.samples.dropwizard.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.File;
import java.util.logging.Level;

/**
 * @author Aleksey Permyakov (06.04.2016).
 */
public class DropwizardConfig extends Configuration {
    @NotEmpty
    private String storagePath;
    private String licensePath;

    @JsonProperty
    public String getStoragePath() {
        if (!new File(storagePath).exists()) {
            java.util.logging.Logger.getLogger(DropwizardConfig.class.getName())
                    .log(Level.SEVERE, "\n\n!!! Storage directory does not exists!!!!\n!!! Path: '" + storagePath + "'!!!\n");
        }
        return storagePath;
    }

    @JsonProperty
    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    @JsonProperty
    public String getLicensePath() {
        if (licensePath == null || licensePath.isEmpty()) {
            licensePath = System.getenv("GROUPDOCS_TOTAL");
        }
        return licensePath;
    }

    @JsonProperty
    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }
}
