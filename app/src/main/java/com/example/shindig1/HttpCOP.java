package com.example.shindig1;
import java.net.*;
import java.io.*;

public class HttpCOP {

    private static URLConnection openWebConnection(String phpFile){
        try{
            URL serv = new URL(phpFile);
            return serv.openConnection();
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static void postData(String phpFile, String urlParameters){//Params should look like: "param1=a&param2=b&param3=c";
        byte[] postData;
        int postDataLength;
        try {
            postData = urlParameters.getBytes("UTF-8");
            postDataLength = postData.length;
            URLConnection urlConnection = openWebConnection(phpFile);
            HttpURLConnection httpurlConnection = (HttpURLConnection) urlConnection;
            httpurlConnection.setDoOutput(true);
            httpurlConnection.setInstanceFollowRedirects( false );
            httpurlConnection.setRequestMethod("POST");

            httpurlConnection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            httpurlConnection.setRequestProperty( "charset", "utf-8");
            httpurlConnection.setRequestProperty( "Content-Length", Integer.toString(postDataLength));

            OutputStream out = httpurlConnection.getOutputStream();
            out.write(postData);

            //httpurlConnection.disconnect();
        }
        catch (Exception e){
            System.out.println(e);
        }


    }
}
