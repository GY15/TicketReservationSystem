package web.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
public class User implements Serializable{

    private String userID;
    private String password;

//    public User(String userID, String password) {
//
//        this.userID = userID;
//        this.password = password;
//    }
    @Id @GeneratedValue
    @Column(name="userid")
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
