package com.drop.auth;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class Authorization {

    private static final String APP_KEY = "dciwrsg941zn4t9";
    private static final String APP_SECRET = "ug1hby11ti2agcq";
    private static final String APP_TOKEN = "Fxb45CxjPNAAAAAAAAAADLyOVt4o0X2lGXuuTBLnavdG9gY1jRcrdWiD38YFmdMq";
    private static final String APP_NAME = "FileReaderTest";

    public DbxClientV2 authDropBoxUser(){
        DbxRequestConfig dbxRequestConfig = DbxRequestConfig.newBuilder(APP_NAME).build();
        DbxClientV2 dbxClientV2 = new DbxClientV2(dbxRequestConfig,APP_TOKEN);
        return dbxClientV2;
    }

}
