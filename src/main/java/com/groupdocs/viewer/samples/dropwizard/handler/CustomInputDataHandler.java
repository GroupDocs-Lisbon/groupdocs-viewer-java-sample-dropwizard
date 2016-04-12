package com.groupdocs.viewer.samples.dropwizard.handler;

import com.groupdocs.viewer.domain.FileDescription;
import com.groupdocs.viewer.domain.options.FileTreeOptions;
import com.groupdocs.viewer.handler.input.IInputDataHandler;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aleksey Permyakov
 */
public class CustomInputDataHandler implements IInputDataHandler {

    @Override
    public FileDescription getFileDescription(String s) {
        return null;
    }

    @Override
    public InputStream getFile(String s) {
        return null;
    }

    @Override
    public Date getLastModificationDate(String s) {
        return null;
    }

    @Override
    public List<FileDescription> loadFileTree(FileTreeOptions fileTreeOptions) {
        return null;
    }
}
