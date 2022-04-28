package ru.exp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.exp.calc.OfferDAOCalculation;
import ru.exp.exception.dao.OfferDAOException;
import ru.exp.mapper.OfferRowMapper;
import ru.exp.model.Offer;

import java.util.*;

@Component
public class OfferDAO {
    private final JdbcTemplate jdbcTemplate;
    private double allPay;
    private double percentPay;
    private double monthPay;
    private List<String> datePay;

    @Autowired
    public OfferDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Offer> index() {
        return jdbcTemplate.query("SELECT * FROM OFFER", new OfferRowMapper());
    }

    public void save(Offer offer) throws OfferDAOException {
        OfferDAOCalculation calculationCredit = new OfferDAOCalculation();

        allPay = calculationCredit.allPayment(offer);
        percentPay = calculationCredit.percentPayment(offer.getPayment(), offer); // percent pay
        monthPay = calculationCredit.monthPayment(allPay, offer); // everyMonth pay
        datePay = calculationCredit.dateMonth(offer); // date payment in month

        offer.setPayment(allPay);
        offer.setPercents(percentPay);
        offer.setEveryMonth(monthPay);
        offer.setDateMas(datePay.toArray(new String[datePay.size()]));

        jdbcTemplate.update("INSERT INTO OFFER (client, years, amount_payment, percent, month_pay, dateList) " +
                                    "VALUES (?, ?, ?, ?, ?, ?)",
                offer.getClient(), offer.getCreditsYear(), offer.getPayment(), offer.getPercents(),
                offer.getEveryMonth(), offer.getDateMas());
    }
}
