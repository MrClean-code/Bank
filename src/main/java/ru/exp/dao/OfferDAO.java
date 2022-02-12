package ru.exp.dao;

import org.springframework.stereotype.Component;
import ru.exp.model.Offer;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

@Component
public class OfferDAO {
    private List<Offer> order;
    private List<Date> date;
    private double p1, pv1;
    private double p2, pv2;
    private double p3, pv3;
    private double p4, pv4;
    private double p5, pv5;
    private double allPay;
    private double percentPay;
    private double monthPay;
    private List<Date> datePay;

    public double getPv1() {
        return pv1;
    }

    public void setPv1(double pv1) {
        this.pv1 = pv1;
    }

    public double getPv2() {
        return pv2;
    }

    public void setPv2(double pv2) {
        this.pv2 = pv2;
    }

    public double getPv3() {
        return pv3;
    }

    public void setPv3(double pv3) {
        this.pv3 = pv3;
    }

    public double getPv4() {
        return pv4;
    }

    public void setPv4(double pv4) {
        this.pv4 = pv4;
    }

    public double getPv5() {
        return pv5;
    }

    public void setPv5(double pv5) {
        this.pv5 = pv5;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getP4() {
        return p4;
    }

    public void setP4(double p4) {
        this.p4 = p4;
    }

    public double getP5() {
        return p5;
    }

    public void setP5(double p5) {
        this.p5 = p5;
    }

    {
        order = new ArrayList<>();
    }

    public List<Offer> index() {
        return order;
    }

    public List<Offer> indexDB() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OFFER");
            while (resultSet.next()) {
                Offer offer = new Offer();
                offer.setId(resultSet.getInt("id"));
                offer.setClient(resultSet.getString("client"));
                offer.setCreditsYear(resultSet.getInt("years"));
                offer.setPayment(resultSet.getDouble("amount_payment"));
                offer.setPercents(resultSet.getDouble("percent"));
                offer.setEveryMonth(resultSet.getDouble("month_pay"));

                order.add(offer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null){
                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }

    public void save(Offer offer) {
        order.add(offer);
        allPay = allPayment(offer); // all credit
        percentPay = percentPayment(offer.getPayment(), offer); // percent pay
        monthPay = monthPayment(allPay, offer); // everyMonth pay
        datePay = dateMonth(offer); // date payment in month

        offer.setPayment(allPay);
        offer.setPercents(percentPay);
        offer.setEveryMonth(monthPay);
        offer.setDatePay(datePay);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

//        Iterator<Date> it = datePay.iterator();
//        Date date = it.next();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\HSQL\\test","SA","SA");
            preparedStatement = connection.prepareStatement("INSERT INTO OFFER (client, years, amount_payment, percent, month_pay) VALUES" +
                            " (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, offer.getClient());
            preparedStatement.setInt(2, offer.getCreditsYear());
            preparedStatement.setDouble(3, offer.getPayment());
            preparedStatement.setDouble(4, offer.getPercents());
            preparedStatement.setDouble(5, offer.getEveryMonth());

            preparedStatement.addBatch();
//            while(it.hasNext()){
//                Date date = it.next();
//                preparedStatement.setTimestamp(6, new Timestamp(date.getTime()));
//                preparedStatement.addBatch();
//            }

            preparedStatement.executeBatch();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Date> dateMonth(Offer offer) {
        date = new ArrayList<>();
        int i = offer.getCreditsYear(); // years
        double offerCreditsMonth = 12 * offer.getCreditsYear();   // all month
        Calendar calendar = new GregorianCalendar();
        LocalDate localDate = LocalDate.now();
        Date dates = null;
        int j = 1;
        if (i == 1) {
            date = oneYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
        } else if (i == 2){
            date = oneYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = twoYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
        } else if (i == 3){
            date = oneYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = twoYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = threeYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
        } else if (i == 4){
            date = oneYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = twoYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = threeYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = fourYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
        } else if (i == 5){
            date = oneYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = twoYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = threeYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = fourYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
            date = fiveYearDate(i, offer, offerCreditsMonth, date, calendar, localDate, dates);
        }

        return date;
    }

    public List<Date> oneYearDate(int i, Offer offer, double offerCreditsMonth, List<Date> date, Calendar calendar,
                                  LocalDate localDate, Date dates){
        int j = 1;
        while(j<=offerCreditsMonth) {
            if (i == 1) {
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                dates = calendar.getTime();
                date.add(dates);
            }
            j++;
        }
        return date;
    }


    public List<Date> twoYearDate(int i, Offer offer, double offerCreditsMonth, List<Date> date, Calendar calendar,
                                  LocalDate localDate, Date dates){
        int j = 1;
        while(j<=offerCreditsMonth) {
            if (i == 2) {
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                dates = calendar.getTime();
                date.add(dates);
            }
            j++;
        }
        return date;
    }

    public List<Date> threeYearDate(int i, Offer offer, double offerCreditsMonth, List<Date> date, Calendar calendar,
                                  LocalDate localDate, Date dates){
        int j = 1;
        while(j<=offerCreditsMonth) {
            if (i == 3){
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                dates = calendar.getTime();
                date.add(dates);
            }
            j++;
        }
        return date;
    }

    public List<Date> fourYearDate(int i, Offer offer, double offerCreditsMonth, List<Date> date, Calendar calendar,
                                    LocalDate localDate, Date dates){
        int j = 1;
        while(j<=offerCreditsMonth) {
            if (i == 4){
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                dates = calendar.getTime();
                date.add(dates);
            }
            j++;
        }
        return date;
    }

    public List<Date> fiveYearDate(int i, Offer offer, double offerCreditsMonth, List<Date> date, Calendar calendar,
                                   LocalDate localDate, Date dates){
        int j = 1;
        while(j<=offerCreditsMonth) {
            if (i == 5){
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                dates = calendar.getTime();
                date.add(dates);
            }
            j++;
        }
        return date;
    }

    public double monthPayment(double allPay, Offer offer) {
        double offerCreditsMonth = 12 * offer.getCreditsYear();   // all month
        double monthPay = allPay / offerCreditsMonth;
        return monthPay;
    }

    public double percentPayment(double allPay, Offer offer) {
        double offerCreditsMonth = 12 * offer.getCreditsYear();   // all month
        double offerPercent = offer.getPercents(); // percent
        int i = offer.getCreditsYear(); // years

        int j = 0;
        while(j<=offerCreditsMonth){
            if (j==offerCreditsMonth && i == 1) {
                onePercent(allPay, offer, offerPercent);
                return getPv1();
            }else if (j==offerCreditsMonth && i == 2) {
                onePercent(allPay, offer, offerPercent);
                twoPercent(allPay, offer, offerPercent);
                return getPv2();
            }else if (j==offerCreditsMonth && i == 3) {
                onePercent(allPay, offer, offerPercent);
                twoPercent(allPay, offer, offerPercent);
                threePercent(allPay, offer, offerPercent);
                return getPv3();
            }else if (j==offerCreditsMonth && i == 4) {
                onePercent(allPay, offer, offerPercent);
                twoPercent(allPay, offer, offerPercent);
                threePercent(allPay, offer, offerPercent);
                fourPercent(allPay, offer, offerPercent);
                return getPv4();
            }else if (j==offerCreditsMonth && i == 5){
                onePercent(allPay, offer, offerPercent);
                twoPercent(allPay, offer, offerPercent);
                threePercent(allPay, offer, offerPercent);
                fourPercent(allPay, offer, offerPercent);
                fivePercent(allPay, offer, offerPercent);
                return getPv5();
            }
            j++;
        }
        return 1.0;
    }

    public double onePercent(double allPay, Offer offer, double offerPercent) {
        double pv1 = (allPay * offerPercent) / 100;
        setPv1(pv1);
        return pv1;
    }

    public double twoPercent(double allPay, Offer offer, double offerPercent) {
        double pv2 = (((getP1() - 12 * monthPayment(getP1(), offer)) * offerPercent) / 100) + getPv1();
        setPv2(pv2);
        return pv2;
    }

    public double threePercent(double allPay, Offer offer, double offerPercent) {
        double pv3 = (((getP2() - 24 * monthPayment(getP2(), offer)) * offerPercent) / 100)  + getPv2();
        setPv3(pv3);
        return pv3;
    }

    public double fourPercent(double allPay, Offer offer, double offerPercent) {
        double pv4 = (((getP3()- 36 * monthPayment(getP3(), offer)) * offerPercent) / 100)  + getPv3();
        setPv4(pv4);
        return pv4;
    }

    public double fivePercent(double allPay, Offer offer, double offerPercent) {
        double pv5 = (((getP4() - 48 * monthPayment(getP4(), offer)) * offerPercent) / 100)  + getPv4();
        setPv5(pv5);
        return pv5;
    }

    public double allPayment(Offer offer) {
        double offerPercent = offer.getPercents(); // percent
        double offerCreditsMonth = 12 * offer.getCreditsYear();   // all month
        int i = offer.getCreditsYear(); // years
        int j = 0;

        while(j<=offerCreditsMonth){
            if (j==offerCreditsMonth && i == 1) {
                oneYear(offer, offerPercent);
                return getP1();
            }else if (j==offerCreditsMonth && i == 2) {
                oneYear(offer, offerPercent);
                twoYear(offer, offerPercent);
                return getP2();
            }else if (j==offerCreditsMonth && i == 3) {
                oneYear(offer, offerPercent);
                twoYear(offer, offerPercent);
                threeYear(offer, offerPercent);
                return getP3();
            }else if (j==offerCreditsMonth && i == 4) {
                oneYear(offer, offerPercent);
                twoYear(offer, offerPercent);
                threeYear(offer, offerPercent);
                fourYear(offer, offerPercent);
                return getP4();
            }else if (j==offerCreditsMonth && i == 5){
                oneYear(offer, offerPercent);
                twoYear(offer, offerPercent);
                threeYear(offer, offerPercent);
                fourYear(offer, offerPercent);
                fiveYear(offer, offerPercent);
                return getP5();
            }
            j++;
        }
        return 0.00;
    }

    public void oneYear(Offer offer, double offerPercent) {
        p1 = offer.getPayment() + onePercent(offer.getPayment(), offer, offerPercent);
        setP1(p1);
    }

    public void twoYear(Offer offer, double offerPercent) {
        p2 = offer.getPayment() + twoPercent(getP1(), offer, offerPercent); //  credit 2 year
        setP2(p2);
    }

    public  void threeYear(Offer offer, double offerPercent) {
        p3 = offer.getPayment() + threePercent(getP2(), offer, offerPercent); //  credit 3 year
        setP3(p3);
    }

    public  void fourYear(Offer offer, double offerPercent) {
        p4 = offer.getPayment() + fourPercent(getP3(), offer, offerPercent); //  credit 4 year
        setP4(p4);
    }

    public  void fiveYear(Offer offer, double offerPercent) {
        p5 = offer.getPayment() + fivePercent(getP4(), offer, offerPercent); //  credit 5 year
        setP5(p5);
    }

}
