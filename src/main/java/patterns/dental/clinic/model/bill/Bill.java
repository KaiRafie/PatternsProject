package patterns.dental.clinic.model.bill;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import patterns.dental.clinic.model.visit.Visit;

import java.sql.Time;
import java.util.Date;


@ToString
public abstract class Bill {
    private long billId;
    private Visit visit;
    private String date;
    private String time;
    private double subTotal;
    private double total;
    private double insuranceDeduction;

    public Bill(long billId, Visit visit, String date, String time, double subTotal, double total, double insuranceDeduction) {
        this.billId = billId;
        this.visit = visit;
        this.date = date;
        this.time = time;
        this.subTotal = subTotal;
        this.total = total;
        this.insuranceDeduction = insuranceDeduction;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getInsuranceDeduction() {
        return insuranceDeduction;
    }

    public void setInsuranceDeduction(double insuranceDeduction) {
        this.insuranceDeduction = insuranceDeduction;
    }

}
