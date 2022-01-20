package model;

public class Material {

    private String materialID;
    private String materialDesc;
    private int price;
    private int weight;


    public Material() {

    }

    public Material(String materialID, String materialDesc, int price, int weight) {
        this.setMaterialID(materialID);
        this.setMaterialDesc(materialDesc);
        this.setPrice(price);
        this.setWeight(weight);
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
