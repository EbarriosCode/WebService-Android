package bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Ebarrios on 07/08/2017.
 */

public interface UsersService {

    @GET("Usuarios")
    Call<List<Users>> getUsers();


    @POST("Usuarios")
    Call<Users> createUser(@Body Users user);
}
