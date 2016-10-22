package forms;

import models.User;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubuntu on 10/22/16.
 */
public class SignUpForm {
    @Constraints.Required
    public String email;

    @Constraints.Required
    public String password;

    @Constraints.Required
    public String firstName;

    @Constraints.Required
    public String lastName;

    public List<ValidationError> validate(){
        List<ValidationError> error = new ArrayList<>();
        User user = User.find.where().eq("email",email).findUnique();

        if(user != null){
            error.add(new ValidationError("message","This email id is already registered"));
            return error;

        }
        return null;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
