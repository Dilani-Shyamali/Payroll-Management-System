package lk.ijse.dep.payroll.entity;

import java.sql.Date;

public class Rate implements SuperEntity {
    private String rateID;
    private Date date;
    private Double ETF;
    private Double EPFEmp;
    private Double EPFCom;

    public Rate() {
    }

    public Rate(String rateID, Date date, Double ETF, Double EPFEmp, Double EPFCom) {
        this.rateID = rateID;
        this.date = date;
        this.ETF = ETF;
        this.EPFEmp = EPFEmp;
        this.EPFCom = EPFCom;
    }

    public String getRateID() {
        return rateID;
    }

    public void setRateID(String rateID) {
        this.rateID = rateID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getETF() {
        return ETF;
    }

    public void setETF(Double ETF) {
        this.ETF = ETF;
    }

    public Double getEPFEmp() {
        return EPFEmp;
    }

    public void setEPFEmp(Double EPFEmp) {
        this.EPFEmp = EPFEmp;
    }

    public Double getEPFCom() {
        return EPFCom;
    }

    public void setEPFCom(Double EPFCom) {
        this.EPFCom = EPFCom;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateID='" + rateID + '\'' +
                ", date=" + date +
                ", ETF=" + ETF +
                ", EPFEmp=" + EPFEmp +
                ", EPFCom=" + EPFCom +
                '}';
    }
}
