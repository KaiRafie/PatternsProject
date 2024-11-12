package patterns.dental.clinic.model.bill;

public class BillModel {
    private BillModel billModel;

    private BillModel() {
    }

    /**
     * insures that the class is a singleton
     * @return a single instance of the class
     */
    public BillModel getInsance() {
        if (billModel == null) {
            synchronized (BillModel.class) {
                if (billModel == null) {
                    billModel = new BillModel();
                }
            }
        }
        return billModel;
    }
}
