package model;

public class Order {

    private String oid;
    private java.time.LocalDate LocalDate;
    private String cusId;
    private double totalAmount;


    public Order() {

    }

    public Order(String oid, java.time.LocalDate localDate, String cusId, double totalAmount) {
        this.setOid(oid);
        setLocalDate(localDate);
        this.setCusId(cusId);
        this.setTotalAmount(totalAmount);
    }


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public java.time.LocalDate getLocalDate() {
        return LocalDate;
    }

    public void setLocalDate(java.time.LocalDate localDate) {
        LocalDate = localDate;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", LocalDate=" + LocalDate +
                ", cusId='" + cusId + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
