package com.sxau.master.data.meta;


import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ResultEnum;
import java.util.Arrays;

public enum FileTypeEnum {
    IMAGE(0,"image/", new String[]{"png", "jpg", "jpeg"},"图片"),
    DOCX(1,"docx/",new String[]{"txt","doc","docx"},"文档"),
    ;
    private int fileType;
    private String fileFolder;
    private String[] fileSuffix;
    private String desc;
    FileTypeEnum(int fileType, String fileFolder, String[] fileSuffix, String desc){
        this.fileType = fileType;
        this.fileFolder = fileFolder;
        this.desc = desc;
        this.fileSuffix = fileSuffix;
    }

    public String[] getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String[] fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFileFolder() {
        return fileFolder;
    }

    public void setFileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
    }

    public static FileTypeEnum getInstance(int type){
        FileTypeEnum[] fileTypeEnums = FileTypeEnum.values();
        for(FileTypeEnum fileTypeEnum: fileTypeEnums){
            if(fileTypeEnum.getFileType() == type){
                return fileTypeEnum;
            }
        }
        throw new ErrorException(ResultEnum.PARAM_ERROR, "枚举不合法");
    }

    public static FileTypeEnum getInstanceByFileSuffix(String fileSuffix){
        FileTypeEnum[] fileTypeEnums = FileTypeEnum.values();
        for(FileTypeEnum fileTypeEnum: fileTypeEnums){
            if (Arrays.asList(fileTypeEnum.getFileSuffix()).contains(fileSuffix)){
                return fileTypeEnum;
            }
        }
        throw new ErrorException(ResultEnum.PARAM_ERROR, "后缀格式不支持");
    }
}
