package bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.pojo.Usuario;

/**
 * Created by Ebarrios on 08/08/2017.
 */

public class UsuarioJSONParser {

    public static List<Usuario> parser(String content)
    {
        try
        {
            JSONArray jsonArray = new JSONArray(content);
            List<Usuario> usuarioList = new ArrayList<>();

            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario usuario = new Usuario();

                usuario.setUsuarioid(jsonObject.getInt("usuarioid"));
                usuario.setNombre(jsonObject.getString("nombre"));
                usuario.setTwitter(jsonObject.getString("twitter"));

                usuarioList.add(usuario);
            }

            return usuarioList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
