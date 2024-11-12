package patterns.dental.clinic.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public abstract class User {
    private String firstName;
    private String lastName;
    //removal of loginID
    private String loginPass;
    private Date dateOfBirth;

    public User(String firstName, String lastName, String loginPass, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginPass = loginPass;
        this.dateOfBirth = dateOfBirth;
    }
}
