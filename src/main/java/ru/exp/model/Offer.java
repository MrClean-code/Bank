package ru.exp.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Offer {
    private int id;

    @NotBlank(message = "client need valid")
    private String client;

    @Min(1)
    @Max(5)
    private int creditsYear;

    @Min(2)
    private double percents;

    @Min(1)
    private double payment;

    private double everyMonth;
    private List<Date> datePay;

    public Offer() {
    }

    public Offer(int id, String client, int creditsYear, double percents, double payment, double everyMonth, List<Date> datePay) {
        this.id = id;
        this.client = client;
        this.creditsYear = creditsYear;
        this.percents = percents;
        this.payment = payment;
        this.everyMonth = everyMonth;
        this.datePay = datePay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getCreditsYear() {
        return creditsYear;
    }

    public void setCreditsYear(int creditsYear) {
        this.creditsYear = creditsYear;
    }

    public double getPercents() {
        return percents;
    }

    public void setPercents(double percents) {
        this.percents = percents;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getEveryMonth() {
        return everyMonth;
    }

    public void setEveryMonth(double everyMonth) {
        this.everyMonth = everyMonth;
    }

    public List<Date> getDatePay() {
        return datePay;
    }

    public void setDatePay(List<Date> datePay) {
        this.datePay = datePay;
    }
}
