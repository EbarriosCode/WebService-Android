package bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import bitacoraelectronica.azucar.com.gt.wservice.R;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models.Users;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models.UsersService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainWebApiActivity extends AppCompatActivity {

    RecyclerView recyclerWebApi;
    TextView tvListaHeroku;
    ProgressBar progress;

    Button btnPost, btnRecargar;

    public static final String URL = "http://www.ebarrios.somee.com/api/";
    private static final String URL2 = "https://androidtutorials.herokuapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web_api);

        recyclerWebApi = (RecyclerView)findViewById(R.id.listaWebApi);
        tvListaHeroku = (TextView)findViewById(R.id.tvListaHeroku);
        tvListaHeroku.setMovementMethod(new ScrollingMovementMethod());

        btnPost = (Button)findViewById(R.id.btnPost);
        btnRecargar = (Button)findViewById(R.id.btnRecargar);
        progress = (ProgressBar)findViewById(R.id.progressHeroku);
        progress.setVisibility(View.INVISIBLE);

        new Peticion().execute();

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainWebApiActivity.this,CreateUserActivity.class));
            }
        });

        btnRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvListaHeroku.setText("");
                new Peticion().execute();
            }
        });
    }

    public void CargarDatos(String s)
    {
        tvListaHeroku.append(s+"\n");
    }

    private class Peticion extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            CargarDatos("Iniciando carga de datos WebApi");
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UsersService service = retrofit.create(UsersService.class);
            Call<List<Users>> response = service.getUsers();

            try {
                for(Users data : response.execute().body())
                {
                    //System.out.println("Response: "+data.getName()+" "+data.getLastName());
                    publishProgress("Response: "+data.getUserName()+" "+data.getLastName());
                    Thread.sleep(100);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            CargarDatos(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            CargarDatos("Carga de datos Finalizada");
            progress.setVisibility(View.INVISIBLE);
        }
    }
}
