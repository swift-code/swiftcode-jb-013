package controllers;

import models.ConnectionRequest;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by lubuntu on 10/23/16.
 */
public class RequestController extends Controller {
    public Result sendRequest(Long senderId, Long reciverId){
        if(senderId == null || reciverId == null || User.find.byId(senderId) != null || User.find.byId(reciverId) != null )
        {
            return ok();
        }
        ConnectionRequest request = new ConnectionRequest();
        request.sender =User.find.byId(reciverId);
        request.reciver = User.find.byId(senderId);
        request.status = ConnectionRequest.Status.WAITING;
        ConnectionRequest.db().insert(request);

        return ok();
    }
    public Result acceptRequest(Long requestId){

        if(requestId == null ||  User.find.byId(requestId) != null )
        {
            return ok();
        }

        ConnectionRequest connect = ConnectionRequest.find.byId(requestId);
        connect.status = ConnectionRequest.Status.ACCEPTED;
        connect.sender.connection.add(connect.reciver);
        connect.reciver.connection.add(connect.sender);

        ConnectionRequest.db().save(connect);
        User.db().save(connect.sender);
        User.db().save(connect.reciver);

        return ok();
    }
}
