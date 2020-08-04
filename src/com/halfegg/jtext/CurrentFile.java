package com.halfegg.jtext;

import java.io.File;

/**
 * Author: halfegg
 */
public class CurrentFile {

    private File file;

    public CurrentFile() {
        file = null;
    }

    public void set(File file) {
        this.file = file;
    }

    public File get() {
        return file;
    }

    public String name() {
        return file == null ? "" : file.getAbsolutePath();
    }
}
