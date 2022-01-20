package model;

public class OderItem {


    private String oderId;
    private String itemCode;
    private int oderQty;
    private double unitPrice;
    private String cusId;
    private double totalAmount;

    public OderItem(String text, String custNameText, String cusNicText, String cusAddressText, String cusNumberText) {
    }

    public OderItem(String oderId, String itemCode, int oderQty, double unitPrice, String cusId, double totalAmount) {
        this.setOderId(oderId);
        this.setItemCode(itemCode);
        this.setOderQty(oderQty);
        this.setUnitPrice(unitPrice);
        this.setCusId(cusId);
        this.setTotalAmount(totalAmount);
    }

    public String getOderId() {
        return oderId;
    }

    public void setOderId(String oderId) {
        this.oderId = oderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOderQty() {
        return oderQty;
    }

    public void setOderQty(int oderQty) {
        this.oderQty = oderQty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
}
