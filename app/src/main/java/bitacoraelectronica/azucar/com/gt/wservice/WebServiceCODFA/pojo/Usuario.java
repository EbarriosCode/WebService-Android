package bitacoraelectronica.azucar.com.gt.wservice.WebServiceCODFA.pojo;

/**
 * Created by Ebarrios on 08/08/2017.
 */

public class Usuario {
    private int usuarioid;
    private String nombre;
    private String twitter;

    public Usuario() {
    }

    public Usuario(int usuarioid, String nombre, String twitter) {
        this.usuarioid = usuarioid;
        this.nombre = nombre;
        this.twitter = twitter;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioid=" + usuarioid +
                ", nombre='" + nombre + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
