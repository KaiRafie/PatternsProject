package patterns.dental.clinic.model.visit;

import lombok.Getter;
import patterns.dental.clinic.model.bill.BillFactory;
import patterns.dental.clinic.model.bill.BillModel;

@Getter
public class VisitFactory {
    private static VisitFactory visitFactory;

    private VisitFactory() {
    }

    public VisitFactory getInstance() {
        if (visitFactory == null) {
            synchronized (VisitFactory.class) {
                if (visitFactory == null) {
                    visitFactory = new VisitFactory();
                }
            }
        }
        return visitFactory;
    }

}
