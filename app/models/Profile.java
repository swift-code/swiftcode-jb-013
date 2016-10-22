package models;

import com.avaje.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by lubuntu on 10/22/16.
 */
public class Profile extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String firstName;
    public String lastName;
    public String Company;

    public Profile(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Model.Find<Long,Profile> find = new Model.Finder<Long, Profile>(Profile.class);

}
