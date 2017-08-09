package bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ebarrios on 08/08/2017.
 */

public class HttpManager {

    public static String getData(String uri)//RequestPackage requestPackage)
    {
        /*
        String uri = requestPackage.getUri();

        if(requestPackage.getMethod().equals("GET"))
        {
            uri += "?"+requestPackage.getEncodeParams();
        }
        */
        BufferedReader reader = null;
        try
        {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String getData(String uri,String username, String password)
    {
        BufferedReader reader = null;

        // enviar el usuario y contraseña para el web service
        byte[] loginBytes = (username + ":"+ password).getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                                                        .append("Basic ")
                                                        .append(Base64.encodeToString(loginBytes,Base64.DEFAULT));
        try
        {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("Authorization",loginBuilder.toString());
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
