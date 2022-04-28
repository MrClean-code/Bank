package ru.exp.dao;

import exception.DeptTestException;
import exception.OfferTestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.exp.config.SpringConfig;
import ru.exp.exception.dao.OfferDAOException;
import ru.exp.model.Dept;
import ru.exp.model.Offer;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class })
public class OfferDAOTest {

    private final JdbcTemplate jdbcTemplate;

    private OfferDAO offerDAO;
    private List<Offer> listOffer;
    private Offer offer;
    private Offer offerUpdate;

    @Autowired
    public OfferDAOTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        offerDAO = new OfferDAO(jdbcTemplate);
    }

    @Test
    public void resIsNullTest() {
        assertThat(jdbcTemplate.equals(null)).isFalse();
        assertThat(offerDAO.equals(null)).isFalse();

        System.out.println("Offer Test not null resources");
    }

    @Test
    public void indexTest() throws OfferTestException {
        listOffer = new ArrayList<>();

        listOffer = offerDAO.index();

        assertThat(listOffer.stream().filter((offer) -> offer.getId() >= 1).findAny()
                .orElseThrow(() -> new OfferTestException("No offer object with id >= 1")));

        System.out.println("Offer Test method index work");
    }

    @Test
    public void saveTest() throws OfferDAOException, OfferTestException {
        offer = new Offer();

        offer.setClient("Mark");
        offer.setCreditsYear(1);
        offer.setPayment(1000);
        offer.setPercents(5);

        offerDAO.save(offer);

        assertThat(offerDAO.index().stream().filter((of) -> (of.getClient() == "Mark")).findAny()
                .orElseThrow(() -> new OfferTestException("No offer object with id = " + offer.getId() +
                        " Client = " + offer.getClient() + " Percent = " + offer.getPercents())));

        System.out.println("Dept Test method save work");
    }
}
