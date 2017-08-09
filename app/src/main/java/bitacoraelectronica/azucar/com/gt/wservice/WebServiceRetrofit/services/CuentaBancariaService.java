package bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.services;

import java.util.List;

import bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models.CuentaBancaria;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ebarrios on 07/08/2017.
 */

public interface CuentaBancariaService {
    @GET("CuentaBancarias")
    Call<List<CuentaBancaria>> getCuentas();
}
