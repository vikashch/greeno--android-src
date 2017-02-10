package com.green0.customerApp.configs;

/**
 * Created by sachinchandra on 10/10/16.
 */

public  class Config {
    //to avoid accendental instantiation
    private Config(){
    }

    public  static final  String BASE_URL = "https://green0.herokuapp.com";
    public  static final  String LOGIN_URL = BASE_URL+"/login";
    // File upload url (replace the ip with your server address)
    public static final String FILE_UPLOAD_URL = "http://192.168.0.104/AndroidFileUpload/fileUpload.php";

    // Directory name to store captured images and videosa
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
}