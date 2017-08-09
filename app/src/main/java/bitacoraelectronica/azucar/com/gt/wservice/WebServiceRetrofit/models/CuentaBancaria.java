package bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models;

/**
 * Created by Ebarrios on 07/08/2017.
 */

public class CuentaBancaria {
    private int NumeroCuenta;
    private String NombreCuenta;

    public CuentaBancaria() {
    }

    public CuentaBancaria(int numeroCuenta, String nombreCuenta) {
        NumeroCuenta = numeroCuenta;
        NombreCuenta = nombreCuenta;
    }

    public int getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        NumeroCuenta = numeroCuenta;
    }

    public String getNombreCuenta() {
        return NombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        NombreCuenta = nombreCuenta;
    }
}
