package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Profile;
import models.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.stream.Collectors;

/**
 * Created by lubuntu on 10/23/16.
 */
public class HomeController extends Controller{
    @Inject
    ObjectMapper objectMapper;

    public Result getProfile(Long id){
        ObjectNode data = objectMapper.createObjectNode();

        User user = User.find.byId(id);
        Profile userProfile = Profile.find.byId(user.profile.id);
        data.put("id",user.id);
        data.put("firstName",userProfile.firstName);
        data.put("lastName",userProfile.lastName);
        data.put("email",user.email);
        data.put("company",userProfile.Company);

        data.set("connections",objectMapper.valueToTree(user.connection.stream().map(Connection -> {
            ObjectNode connectionJson = objectMapper.createObjectNode();
            User connectionUser = User.find.byId(Connection.id);
            Profile connectionProfile = Profile.find.byId(Connection.profile.id);
            connectionJson.put("id",connectionUser.id);
            connectionJson.put("firstName",connectionProfile.firstName);
            connectionJson.put("lastName",connectionProfile.lastName);
            connectionJson.put("email",connectionUser.email);
            connectionJson.put("company",connectionProfile.Company);
            return connectionJson;
        }).collect(Collectors.toList())));

        return ok();
    }
}
