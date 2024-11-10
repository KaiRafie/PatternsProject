package patterns.dentalclinic;

import lombok.*;
import java.util.ArrayList;

@Getter
@Setter

public class ApprenticeDentist extends Dentist{
    private ArrayList<String> allowedOperations;
}
