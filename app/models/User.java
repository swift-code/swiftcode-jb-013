package models;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by lubuntu on 10/22/16.
 */
public class User extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String email;
    public String password;

    @OneToOne
    public Profile profile;

    @ManyToMany
    @JoinTable(name = "user_connection",
        joinColumns = {
                @JoinColumn(
                        name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "connection_id")})
    public Set<User> connection;

    @OneToMany(mappedBy = "sender")
    public List<ConnectionRequest> connectionRequestsSent;

    @OneToMany(mappedBy = "reciver")
    public List<ConnectionRequest> connectionRequestsRecived;

    public static Model.Find<Long,User> find = new Model.Finder<Long, User>(User.class);

    public  User(String user,String password){
        this.email = user;
        this.password = BCrypt.hashpw(password,BCrypt.gensalt());
    }

}
