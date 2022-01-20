package model;

public class OrderTm {

   private String id;
   private String desc;
   private int qty;
   private double unitPrice;
   private double total;

    public OrderTm(String id, String desc, int qty, double unitPrice, double total) {
        this.setId(id);
        this.setDesc(desc);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setTotal(total);
    }

    public OrderTm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }




    @Override
    public String toString() {
        return "OrderTm{" +
                "id='" + getId() + '\'' +
                ", desc='" + getDesc() + '\'' +
                ", qty=" + getQty() +
                ", unitPrice=" + getUnitPrice() +
                ", total=" + getTotal() +
                '}';
    }


}
