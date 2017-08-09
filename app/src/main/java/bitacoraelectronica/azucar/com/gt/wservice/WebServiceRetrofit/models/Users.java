package bitacoraelectronica.azucar.com.gt.wservice.WebServiceRetrofit.models;

/**
 * Created by Ebarrios on 07/08/2017.
 */

public class Users {
    private int UserId;
    private String UserName;
    private String LastName;
    private int Phone;
    private String City;

    public Users() {
    }

    public Users( String userName, String lastName, int phone, String city) {
        UserName = userName;
        LastName = lastName;
        Phone = phone;
        City = city;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
