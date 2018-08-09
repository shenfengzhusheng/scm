package org.xfs.scm.data.business.common.model;

import org.springframework.web.multipart.MultipartFile;

public class FileVo {

    private MultipartFile file;
    private String fileName;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
