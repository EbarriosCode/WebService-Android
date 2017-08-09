package bitacoraelectronica.azucar.com.gt.wservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.HttpManager;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.adapters.recyclerViewAdapter;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.parsers.UsuarioJSONParser;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.pojo.Usuario;

public class MainRecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Usuario> usuariosList;
    recyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerDatosWebService);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        if(isOnline())
        {
            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/usuarios.xml");
            pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservice.php");
            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/s/webservice.php");
            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/webserviceI.php");
        }
        else
        {
            Toast.makeText(getApplicationContext(), "SIN CONEXIÓN", Toast.LENGTH_SHORT).show();
        }
    }

    public void cargarDatos() {

        adapter = new recyclerViewAdapter(getApplicationContext(),usuariosList);
        recyclerView.setAdapter(adapter);
    }

    public boolean isOnline()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting())
        {
            System.out.println("Conexion a internet EXITOSA");
            return true;
        }
        else
        {
            System.out.println("Conexión a internet FALLIDA");
            return false;
        }

    }

    public void pedirDatos(String url)
    {
        MyTask task = new MyTask();
        task.execute(url);
        //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class MyTask extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //cargarDatos("Inicio de carga");


        }

        @Override
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            return content;
            //return "Terminamos";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //cargarDatos(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result == null)
            {
                Toast.makeText(MainRecyclerActivity.this, "NO SE PUEDO CONECTAR CON EL WEBSERVICE", Toast.LENGTH_SHORT).show();
               // progressBar.setVisibility(View.INVISIBLE);
                return;
            }

            usuariosList = UsuarioJSONParser.parser(result);
            cargarDatos();

        }
    }
}
