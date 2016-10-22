package forms;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubuntu on 10/22/16.
 */
public class LoginForm {
    @Constraints.Required
    public String email;

    @Constraints.Required
    public String password;

    public List<ValidationError> validate(){
        List<ValidationError> error = new ArrayList();
        User user = User.find.where().eq("email",email).findUnique();

        if(user == null){
            error.add(new ValidationError("message","Incorrect email or password"));
        }
        else if(BCrypt.checkpw(password, user.password)){
            error.add(new ValidationError("message","Incorrect email or password"));
        }
        return  error;
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
