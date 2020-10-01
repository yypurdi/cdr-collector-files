/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micro.msb.collector;

/**
 *
 * @author Yan Yan Purdiansah
 */
public class CDRMessage {

    private String fileDirectoryInput;
    private String fileDirectoryOutput;
    private String fileType;
    private String fileName;
    private String fileExt;

    public String getFileDirectoryInput() {
        return fileDirectoryInput;
    }

    public void setFileDirectoryInput(String fileDirectoryInput) {
        this.fileDirectoryInput = fileDirectoryInput;
    }

    public String getFileDirectoryOutput() {
        return fileDirectoryOutput;
    }

    public void setFileDirectoryOutput(String fileDirectoryOutput) {
        this.fileDirectoryOutput = fileDirectoryOutput;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }    
}
