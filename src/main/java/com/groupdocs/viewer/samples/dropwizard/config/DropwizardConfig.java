package com.groupdocs.viewer.samples.dropwizard.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.File;
import java.util.logging.Level;

/**
 * The type Dropwizard config.
 * @author Aleksey Permyakov (06.04.2016).
 */
public class DropwizardConfig extends Configuration {
    @NotEmpty
    private String storagePath;
    private String licensePath;

    /**
     * Gets storage path.
     * @return the storage path
     */
    @JsonProperty
    public String getStoragePath() {
        if (!new File(storagePath).exists()) {
            java.util.logging.Logger.getLogger(DropwizardConfig.class.getName())
                    .log(Level.SEVERE, "\n\n!!! Storage directory does not exists!!!!\n!!! Path: '" + storagePath + "'!!!\n");
        }
        return storagePath;
    }

    /**
     * Sets storage path.
     * @param storagePath the storage path
     */
    @JsonProperty
    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Gets license path.
     * @return the license path
     */
    @JsonProperty
    public String getLicensePath() {
        if (licensePath == null || licensePath.isEmpty()) {
            licensePath = System.getenv("GROUPDOCS_TOTAL");
        }
        return licensePath;
    }

    /**
     * Sets license path.
     * @param licensePath the license path
     */
    @JsonProperty
    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }
}
