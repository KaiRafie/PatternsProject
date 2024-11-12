package patterns.dental.clinic.model.bill;

import lombok.Getter;
import lombok.Setter;
import patterns.dental.clinic.model.Visit;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public abstract class Bill {
    private long billId;
    private Visit visit;
    private Date date;
    private Time time;
    private double subTotal;
    private double total;
    private double insuranceDeduction;

    public Bill(long billId, Visit visit, Date date, Time time, double subTotal, double total, double insuranceDeduction) {
        this.billId = billId;
        this.visit = visit;
        this.date = date;
        this.time = time;
        this.subTotal = subTotal;
        this.total = total;
        this.insuranceDeduction = insuranceDeduction;
    }

    abstract long generateId();
}
