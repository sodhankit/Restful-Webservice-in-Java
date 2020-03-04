package controller;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("id")
@Expose
private int id;
@SerializedName("fullName")
@Expose
private String fullName;
@SerializedName("email")
@Expose
private String email;
@SerializedName("password")
@Expose
private String password;

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

}