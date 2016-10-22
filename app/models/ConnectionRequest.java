package models;

import com.avaje.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by lubuntu on 10/22/16.
 */
public class ConnectionRequest extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public User sender;

    @ManyToOne
    public User reciver;

    public Status status;
    public enum Status{
        WAITING,ACCEPTED
    }
    public static Model.Find<Long,ConnectionRequest> find = new Model.Finder<Long, ConnectionRequest>(ConnectionRequest.class);
}
