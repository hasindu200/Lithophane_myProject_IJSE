package model;

public class Item {

    private String itemCode;
    private String materialId;
    private int unitPrice;
    private int qty;
    private String itemDes;

    public Item() {
    }

    public Item(String itemCode, int unitPrice, int qty, String itemDes) {
        this.itemCode = itemCode;
       // this.materialId = materialId;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.itemDes = itemDes;
    }



    public Item(String text, String materialDesText, int i, int parseInt) {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getItemDes() {
        return itemDes;
    }

    public void setItemDes(String itemDes) {
        this.itemDes = itemDes;
    }
}
