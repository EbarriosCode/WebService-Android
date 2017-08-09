package bitacoraelectronica.azucar.com.gt.wservice;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.HttpManager;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.adapters.listViewAdapter;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.parsers.UsuarioJSONParser;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.pojo.Usuario;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.activities.MainWebApiActivity;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models.Users;

public class MainActivity extends AppCompatActivity {
    Button btnDatos, btnRecycler,btnIrApi;
    TextView tvDatos;
    ProgressBar progressBar;

    List<MyTask> taskList;

    //lista de Obj Usuarios
    List<Usuario> usuariosList;

    ListView listViewWebService;
    listViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatos = (Button)findViewById(R.id.btnDatos);
        btnIrApi =(Button)findViewById(R.id.btnIrApi);
        btnRecycler =(Button)findViewById(R.id.btnRecycler);
        tvDatos = (TextView)findViewById(R.id.tvDatos);
        tvDatos.setMovementMethod(new ScrollingMovementMethod());

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        listViewWebService = (ListView)findViewById(R.id.listview);
        taskList = new ArrayList<>();
        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for(int i = 1; i<=100 ; i++)
                {
                    cargarDatos("Número: "+i);
                }*/
                if(isOnline())
                {
                    //pedirDatos("http://maloschistes.com/maloschistes.com/jose/usuarios.xml");
                    //pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservice.php");
                    //pedirDatos("http://maloschistes.com/maloschistes.com/jose/s/webservice.php");
                    pedirDatos("http://maloschistes.com/maloschistes.com/jose/webserviceI.php");
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "SIN CONEXIÓN", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnIrApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainWebApiActivity.class));
            }
        });

        btnRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainRecyclerActivity.class));
            }
        });

        Users u = new Users("DEV","ANDROID",123456,"GOOGLE");
        System.out.println(u.toString());
        new MyTaskPost().execute(u);
    }

    public void cargarDatos() {

        //tvDatos.append(s + "\n");
        /*if(usuariosList != null)
        {
            for (Usuario data : usuariosList)
            {
                tvDatos.append("Name -> "+data.getNombre()+" Twitter ->"+data.getTwitter()+"\n");
            }

        }*/
        adapter = new listViewAdapter(getApplicationContext(),usuariosList);
        listViewWebService.setAdapter(adapter);
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

    private class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //cargarDatos("Inicio de carga");

            if(taskList.size() == 0){
                progressBar.setVisibility(View.VISIBLE);
            }

            taskList.add(this);
        }

        @Override
        protected String doInBackground(String... params) {
            /*for(int i = 1; i<=10 ; i++)
            {
                publishProgress("Número: "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } */
            String content = HttpManager.getData(params[0],"pepito","pepito");
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
                Toast.makeText(MainActivity.this, "NO SE PUEDO CONECTAR CON EL WEBSERVICE", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }

            usuariosList = UsuarioJSONParser.parser(result);
            cargarDatos();
            taskList.remove(this);
            if(taskList.size() == 0){
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    private class MyTaskPost extends AsyncTask<Users,Void,Void>{

        @Override
        protected Void doInBackground(Users... params) {
            String uri = "http://www.ebarrios.somee.com/api/Usuarios";
            Log.d("Objeto"," ->> "+params[0].toString());
            HttpManager.postData(params[0],uri);
            return null;
        }
    }
}
