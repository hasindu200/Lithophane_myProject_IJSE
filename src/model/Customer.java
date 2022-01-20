package model;

public class Customer {


    private String cusId;
    private String cusName;
    private String cusNic;
    private String cusAddress;
    private String cusNumber;


    public Customer() {
    }

    public Customer(String cusId, String cusName, String cusNic, String cusAddress, String cusNumber) {
        this.setCusId(cusId);
        this.setCusName(cusName);
        this.setCusNic(cusNic);
        this.setCusAddress(cusAddress);
        this.setCusNumber(cusNumber);
    }


    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusNic() {
        return cusNic;
    }

    public void setCusNic(String cusNic) {
        this.cusNic = cusNic;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }
}

