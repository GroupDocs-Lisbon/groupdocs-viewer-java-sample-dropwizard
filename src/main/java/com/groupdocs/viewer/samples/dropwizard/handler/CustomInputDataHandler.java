package com.groupdocs.viewer.samples.dropwizard.handler;

import com.groupdocs.viewer.domain.FileDescription;
import com.groupdocs.viewer.domain.options.FileTreeOptions;
import com.groupdocs.viewer.handler.input.IInputDataHandler;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * The type Custom input data handler.
 * @author Aleksey Permyakov
 */
public class CustomInputDataHandler implements IInputDataHandler {

    /**
     * Gets file description.
     * @param s the s
     * @return the file description
     */
    @Override
    public FileDescription getFileDescription(String s) {
        return null;
    }

    /**
     * Gets file.
     * @param s the s
     * @return the file
     */
    @Override
    public InputStream getFile(String s) {
        return null;
    }

    /**
     * Gets last modification date.
     * @param s the s
     * @return the last modification date
     */
    @Override
    public Date getLastModificationDate(String s) {
        return null;
    }

    /**
     * Load file tree list.
     * @param fileTreeOptions the file tree options
     * @return the list
     */
    @Override
    public List<FileDescription> loadFileTree(FileTreeOptions fileTreeOptions) {
        return null;
    }
}
