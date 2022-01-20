package model;

import javafx.scene.Parent;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

public class OderDetails {

    private  String oid;
    private String itemId;
    private String cusId;
    private int qty;
    private double unitPrice;
    private double totalAmount;

    private List<OderDetails> details;

    public OderDetails() {
    }

    public OderDetails(String oid, String itemId, String cusId, int qty, double unitPrice, double totalAmount, List<OderDetails> details) {
        this.oid = oid;
        this.itemId = itemId;
        this.cusId = cusId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.details = details;
    }

    public String getOid() {
        return oid;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OderDetails> getDetails() {
        return details;
    }

    public void setDetails(List<OderDetails> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "OderDetails{" +
                "oid='" + oid + '\'' +
                ", itemId='" + itemId + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", totalAmount=" + totalAmount +
                ", details=" + details +
                '}';
    }
}
