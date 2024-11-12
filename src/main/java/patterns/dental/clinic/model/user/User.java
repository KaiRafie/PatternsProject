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
    private String loginID;
    private String loginPass;
    private Date dateOfBirth;
}
