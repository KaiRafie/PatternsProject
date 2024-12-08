package patterns.dental.clinic;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> extends ArrayList<T> {

    /**
     * To be able to have a toString with no {}, We had to create this class and override the toString method.
     * @return a String of the element inside the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size(); i++) {
            sb.append(get(i)).append(", ");
        }

        return sb.toString();
    }
}
