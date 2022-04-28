package ru.exp.calc;

import ru.exp.exception.dao.OfferDAOException;
import ru.exp.model.Offer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class OfferDAOCalculation {

    private double p1, pv1;
    private double p2, pv2;
    private double p3, pv3;
    private double p4, pv4;
    private double p5, pv5;
    private double monthPay;
    private double offerCreditsMonth;
    private double offerPercent;
    private String datesStr;
    private List<String> dateStrList;
    private SimpleDateFormat sdf;
    private Calendar calendar;
    private LocalDate localDate;

    public List<String> dateMonth(Offer offer) {
        dateStrList = new ArrayList<>();
        calendar = new GregorianCalendar();
        localDate = LocalDate.now();
        offerCreditsMonth = 12 * offer.getCreditsYear();   // all month
        sdf = new SimpleDateFormat("dd/M/yyyy");    // format time
        int i = offer.getCreditsYear();                    // years
        if (i == 1) {
            dateStrList = oneYearDate(i, offerCreditsMonth, dateStrList);
        } else if (i == 2) {
            dateStrList = oneYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = twoYearDate(i, offerCreditsMonth, dateStrList);
        } else if (i == 3) {
            dateStrList = oneYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = twoYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = threeYearDate(i, offerCreditsMonth, dateStrList);
        } else if (i == 4) {
            dateStrList = oneYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = twoYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = threeYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = fourYearDate(i, offerCreditsMonth, dateStrList);
        } else if (i == 5) {
            dateStrList = oneYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = twoYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = threeYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = fourYearDate(i, offerCreditsMonth, dateStrList);
            dateStrList = fiveYearDate(i, offerCreditsMonth, dateStrList);
        }
        return dateStrList;
    }

    public List<String> oneYearDate(int i, double offerCreditsMonth, List<String> dateStrList){
        int j = 1;
        datesStr = null;
        while(j<=offerCreditsMonth) {
            if (i == 1) {
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                datesStr = sdf.format(calendar.getTime());
                dateStrList.add(datesStr);
            }
            j++;
        }
        return dateStrList;
    }


    public List<String> twoYearDate(int i, double offerCreditsMonth, List<String> dateStrList) {
        int j = 1;
        datesStr = null;
        while(j<=offerCreditsMonth) {
            if (i == 2) {
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                datesStr = sdf.format(calendar.getTime());
                dateStrList.add(datesStr);
            }
            j++;
        }
        return dateStrList;
    }

    public List<String> threeYearDate(int i, double offerCreditsMonth, List<String> dateStrList) {
        int j = 1;
        datesStr = null;
        while(j<=offerCreditsMonth) {
            if (i == 3){
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                datesStr = sdf.format(calendar.getTime());
                dateStrList.add(datesStr);
            }
            j++;
        }
        return dateStrList;
    }

    public List<String> fourYearDate(int i, double offerCreditsMonth, List<String> dateStrList) {
        int j = 1;
        datesStr = null;
        while(j<=offerCreditsMonth) {
            if (i == 4){
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                datesStr = sdf.format(calendar.getTime());
                dateStrList.add(datesStr);
            }
            j++;
        }
        return dateStrList;
    }

    public List<String> fiveYearDate(int i, double offerCreditsMonth, List<String> dateStrList) {
        int j = 1;
        datesStr = null;
        while(j<=offerCreditsMonth) {
            if (i == 5){
                calendar.set(Calendar.YEAR, localDate.getYear());
                calendar.set(Calendar.MONTH, localDate.getMonthValue() - 2 + j);
                datesStr = sdf.format(calendar.getTime());
                dateStrList.add(datesStr);
            }
            j++;
        }
        return dateStrList;
    }

    public double monthPayment(double allPay, Offer offer) {
        offerCreditsMonth = 12 * offer.getCreditsYear();   // all month
        monthPay = allPay / offerCreditsMonth;
        return monthPay;
    }

    public double percentPayment(double allPay, Offer offer) throws OfferDAOException {
        offerCreditsMonth = 12 * offer.getCreditsYear();   // all month
        offerPercent = offer.getPercents(); // percent
        int i = offer.getCreditsYear(); // years

        int j = 0;
        while(j<=offerCreditsMonth) {
            if (j==offerCreditsMonth && i == 1) {
                onePercent(allPay, offerPercent);
                return pv1;
            } else if (j==offerCreditsMonth && i == 2) {
                onePercent(allPay, offerPercent);
                twoPercent(offer, offerPercent);
                return pv2;
            } else if (j==offerCreditsMonth && i == 3) {
                onePercent(allPay, offerPercent);
                twoPercent(offer, offerPercent);
                threePercent(offer, offerPercent);
                return pv3;
            } else if (j==offerCreditsMonth && i == 4) {
                onePercent(allPay, offerPercent);
                twoPercent(offer, offerPercent);
                threePercent(offer, offerPercent);
                fourPercent(offer, offerPercent);
                return pv4;
            } else if (j==offerCreditsMonth && i == 5) {
                onePercent(allPay, offerPercent);
                twoPercent(offer, offerPercent);
                threePercent(offer, offerPercent);
                fourPercent(offer, offerPercent);
                fivePercent(offer, offerPercent);
                return pv5;
            }
            j++;
        }
        throw new OfferDAOException("Exception when calc percent method: percentPayment(), class: " + getClass());
    }

    public double onePercent(double allPay, double offerPercent) {
        double pv1 = (allPay * offerPercent) / 100;
        this.pv1 = pv1;
        return pv1;
    }

    public double twoPercent(Offer offer, double offerPercent) {
        double pv2 = (((p1 - 12 * monthPayment(p1, offer)) * offerPercent) / 100) + pv1;
        this.pv2 = pv2;
        return pv2;
    }

    public double threePercent(Offer offer, double offerPercent) {
        double pv3 = (((p2 - 24 * monthPayment(p2, offer)) * offerPercent) / 100)  + pv2;
        this.pv3 = pv3;
        return pv3;
    }

    public double fourPercent(Offer offer, double offerPercent) {
        double pv4 = (((p3- 36 * monthPayment(p3, offer)) * offerPercent) / 100)  + pv3;
        this.pv4 = pv4;
        return pv4;
    }

    public double fivePercent(Offer offer, double offerPercent) {
        double pv5 = (((p4 - 48 * monthPayment(p4, offer)) * offerPercent) / 100)  + pv4;
        this.pv5 = pv5;
        return pv5;
    }

    public double allPayment(Offer offer) throws OfferDAOException {
        offerPercent = offer.getPercents(); // percent
        offerCreditsMonth = 12 * offer.getCreditsYear();   // all month
        int i = offer.getCreditsYear(); // years
        int j = 0;

        while(j<=offerCreditsMonth) {
            if (j==offerCreditsMonth && i == 1) {
                oneYear(offer, offerPercent);
                return p1;
            } else if (j==offerCreditsMonth && i == 2) {
                oneYear(offer, offerPercent);
                twoYear(offer, offerPercent);
                return p2;
            } else if (j==offerCreditsMonth && i == 3) {
                oneYear(offer, offerPercent);
                twoYear(offer, offerPercent);
                threeYear(offer, offerPercent);
                return p3;
            } else if (j==offerCreditsMonth && i == 4) {
                oneYear(offer, offerPercent);
                twoYear(offer, offerPercent);
                threeYear(offer, offerPercent);
                fourYear(offer, offerPercent);
                return p4;
            } else if (j==offerCreditsMonth && i == 5) {
                oneYear(offer, offerPercent);
                twoYear(offer, offerPercent);
                threeYear(offer, offerPercent);
                fourYear(offer, offerPercent);
                fiveYear(offer, offerPercent);
                return p5;
            }
            j++;
        }
        throw new OfferDAOException("Exception when calc all sum credit, method: allPayment(), class: " + getClass());
    }

    public void oneYear(Offer offer, double offerPercent) {
        double p1 = offer.getPayment() + onePercent(offer.getPayment(), offerPercent);
        this.p1 = p1;
    }

    public void twoYear(Offer offer, double offerPercent) {
        double p2 = offer.getPayment() + twoPercent(offer, offerPercent); //  credit 2 year
        this.p2 = p2;
    }

    public void threeYear(Offer offer, double offerPercent) {
        double p3 = offer.getPayment() + threePercent(offer, offerPercent); //  credit 3 year
        this.p3 = p3;
    }

    public void fourYear(Offer offer, double offerPercent) {
        double p4 = offer.getPayment() + fourPercent(offer, offerPercent); //  credit 4 year
        this.p4 = p4;
    }

    public void fiveYear(Offer offer, double offerPercent) {
        double p5 = offer.getPayment() + fivePercent(offer, offerPercent); //  credit 5 year
        this.p5 = p5;
    }
}
