package model;

public class Delivery {



    private String deliveryId;
    private String contactNum;
    private String deliveryAddress;
    private String custId;


    public Delivery() {
    }

    public Delivery(String deliveryId, String contactNum, String deliveryAddress, String custId) {
        this.setDeliveryId(deliveryId);
        this.setContactNum(contactNum);
        this.setDeliveryAddress(deliveryAddress);
        this.setCustId(custId);
    }


    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }
}
