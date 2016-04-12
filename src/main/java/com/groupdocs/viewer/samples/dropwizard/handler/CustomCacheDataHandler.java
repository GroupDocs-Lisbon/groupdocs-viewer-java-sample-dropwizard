package com.groupdocs.viewer.samples.dropwizard.handler;

import com.groupdocs.viewer.domain.CacheFileDescription;
import com.groupdocs.viewer.handler.cache.ICacheDataHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author Aleksey Permyakov
 */
public class CustomCacheDataHandler implements ICacheDataHandler {
    @Override
    public boolean exists(CacheFileDescription cacheFileDescription) {
        return false;
    }

    @Override
    public InputStream getInputStream(CacheFileDescription cacheFileDescription) {
        return null;
    }

    @Override
    public OutputStream getOutputSaveStream(CacheFileDescription cacheFileDescription) {
        return null;
    }

    @Override
    public Date getLastModificationDate(CacheFileDescription cacheFileDescription) {
        return null;
    }
}
