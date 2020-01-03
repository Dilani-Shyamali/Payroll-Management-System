package lk.ijse.dep.payroll.dto;

import java.sql.Date;

public class RateDTO {
    private String rateID;
    private Date date;
    private double ETF;
    private double EPFEmp;
    private double EPFCom;

    public RateDTO() {
    }

    public RateDTO(String rateID, Date date, double ETF, double EPFEmp, double EPFCom) {
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

    public double getETF() {
        return ETF;
    }

    public void setETF(double ETF) {
        this.ETF = ETF;
    }

    public double getEPFEmp() {
        return EPFEmp;
    }

    public void setEPFEmp(double EPFEmp) {
        this.EPFEmp = EPFEmp;
    }

    public double getEPFCom() {
        return EPFCom;
    }

    public void setEPFCom(double EPFCom) {
        this.EPFCom = EPFCom;
    }

    @Override
    public String toString() {
        return "RateDTO{" +
                "rateID='" + rateID + '\'' +
                ", date=" + date +
                ", ETF=" + ETF +
                ", EPFEmp=" + EPFEmp +
                ", EPFCom=" + EPFCom +
                '}';
    }
}
