package ru.exp.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.exp.model.Offer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferRowMapper implements RowMapper<Offer> {

    @Override
    public Offer mapRow(ResultSet resultSet, int i) throws SQLException {
        Offer offer = new Offer();

        offer.setId(resultSet.getInt("id"));
        offer.setClient(resultSet.getString("client"));
        offer.setCreditsYear(resultSet.getInt("years"));
        offer.setPayment(resultSet.getDouble("amount_payment"));
        offer.setPercents(resultSet.getDouble("percent"));
        offer.setEveryMonth(resultSet.getDouble("month_pay"));
        offer.setMasStr(resultSet.getArray("dateList"));

        return offer;
    }
}
