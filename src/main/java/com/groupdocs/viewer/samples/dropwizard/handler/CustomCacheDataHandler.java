package com.groupdocs.viewer.samples.dropwizard.handler;

import com.groupdocs.viewer.domain.CacheFileDescription;
import com.groupdocs.viewer.handler.cache.ICacheDataHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * The type Custom cache data handler.
 * @author Aleksey Permyakov
 */
public class CustomCacheDataHandler implements ICacheDataHandler {
    /**
     * Exists boolean.
     * @param cacheFileDescription the cache file description
     * @return the boolean
     */
    @Override
    public boolean exists(CacheFileDescription cacheFileDescription) {
        return false;
    }

    /**
     * Gets input stream.
     * @param cacheFileDescription the cache file description
     * @return the input stream
     */
    @Override
    public InputStream getInputStream(CacheFileDescription cacheFileDescription) {
        return null;
    }

    /**
     * Gets output save stream.
     * @param cacheFileDescription the cache file description
     * @return the output save stream
     */
    @Override
    public OutputStream getOutputSaveStream(CacheFileDescription cacheFileDescription) {
        return null;
    }

    /**
     * Gets last modification date.
     * @param cacheFileDescription the cache file description
     * @return the last modification date
     */
    @Override
    public Date getLastModificationDate(CacheFileDescription cacheFileDescription) {
        return null;
    }
}
