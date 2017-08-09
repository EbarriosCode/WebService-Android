package bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bitacoraelectronica.azucar.com.gt.wservice.R;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models.Users;
import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models.UsersService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateUserActivity extends AppCompatActivity {

    EditText edtUserName, edtLastName, edtPhone, edtCity;
    Button btnEnviar;

    /* datos para el asynctask */
    String userName;
    String lastName;
    String phone;
    int phoneNumber;
    String city;
    Users usuario = null;
    /* datos para el asynctask */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtLastName = (EditText)findViewById(R.id.edtLastName);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
        edtCity = (EditText)findViewById(R.id.edtCity);
        btnEnviar = (Button)findViewById(R.id.btnCreatePost);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
            }
        });
    }

    public void PostRetrofit(Users user)
    {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(MainWebApiActivity.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();
        UsersService service = retrofit.create(UsersService.class);

        Call<Users> call = service.createUser(user);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(CreateUserActivity.this, "Exito Creado ->"+ response.body().getUserName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(CreateUserActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            userName = edtUserName.getText().toString();
            lastName = edtLastName.getText().toString();
            phone = edtPhone.getText().toString();
             phoneNumber = Integer.parseInt(phone);
            city = edtCity.getText().toString();
            usuario = new Users(userName,lastName,phoneNumber,city);

            System.out.println("Inicio de hilo obteniendo datos de la UI");
        }

        @Override
        protected String doInBackground(String... params) {

            PostRetrofit(usuario);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("Tarea finalizada");
        }
    }
}
