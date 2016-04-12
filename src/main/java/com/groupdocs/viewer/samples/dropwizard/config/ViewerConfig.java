package com.groupdocs.viewer.samples.dropwizard.config;

import com.groupdocs.viewer.licensing.License;

import java.io.File;

/**
 *
 * @author Aleksey Permyakov
 */
public class ViewerConfig extends com.groupdocs.viewer.config.ViewerConfig {
    public final String LICENSE_PATH;
    public final String STORAGE_PATH;

    public ViewerConfig(DropwizardConfig configuration) {
        STORAGE_PATH = configuration.getStoragePath();
        LICENSE_PATH = configuration.getLicensePath();

        if (LICENSE_PATH != null) {
            new License().setLicense(LICENSE_PATH);
        }
    }

    @Override
    public String getStoragePath() {
        return STORAGE_PATH;
    }

    @Override
    public String getCachePath() {
        final File cache = new File(STORAGE_PATH + "\\cache\\");
        if (!cache.exists() && !cache.mkdirs()) {
            System.out.println("Problem with cache path!");
        }
        return cache.getAbsolutePath();
    }

    @Override
    public String getTempPath() {
        final File temp = new File(STORAGE_PATH + "\\temp\\");
        if (!temp.exists() && !temp.mkdirs()) {
            System.out.println("Problem with temp path!");
        }
        return temp.getAbsolutePath();
    }

    @Override
    public boolean getUseCache() {
        return false;
    }

    @Override
    public boolean getUsePdf() {
        return false;
    }
}