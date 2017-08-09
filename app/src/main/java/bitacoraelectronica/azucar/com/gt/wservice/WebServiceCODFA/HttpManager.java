package bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models.Users;

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

    public static void postData(Users usuario,String uri)
    {
        System.out.println("Objeto en el HttpDataManager -> "+usuario.toString());
        //Serializar a json el objeto que se va a enviar el WebService
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        try {
            json.put("UserName", usuario.getUserName());
            json.put("LastName", usuario.getLastName());
            json.put("Phone", usuario.getPhone());
            json.put("City", usuario.getCity());

            //agregar el json a un array
            jsonArray.put(json);
            Log.d("JSON POST API -->"," Json Array convertido"+jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // conectar con el web service
        try {
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            //OutputStream os = httpURLConnection.getOutputStream();
            //os.write(jsonArray.toString().getBytes());
            byte[] outputBytes = jsonArray.toString().getBytes("UTF-8");
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(outputBytes);

            if(httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED)
            {
                throw new RuntimeException("Falló: http error code: "+httpURLConnection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String output;
            System.out.println("Salida hacia el server ... \n");
            while((output = br.readLine()) != null)
            {
                System.out.println(output);
            }

            httpURLConnection.disconnect();
            /*OutputStream stream = httpURLConnection.getOutputStream();
            stream.write(jsonArray.toString().getBytes());

            InputStream inputStream = httpURLConnection.getInputStream();
            String resultado = "";
            int byteCharacter;

            while((byteCharacter = inputStream.read()) != -1)
            {
                resultado += (char)byteCharacter;
            }
            System.out.println("Valor del resultado: ->> "+resultado);
            inputStream.close();
            stream.close();
            */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
