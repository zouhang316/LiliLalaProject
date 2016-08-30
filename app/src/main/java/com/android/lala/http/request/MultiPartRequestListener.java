package com.android.lala.http.request;

import java.io.File;
import java.util.Map;

/**
 * @author ssx
 */
public interface MultiPartRequestListener {

    public void addFileUpload(String param, File file);
    
    public void addStringUpload(String param, String content);
    
    public Map<String,File> getFileUploads();
    
    public Map<String,String> getStringUploads(); 
}