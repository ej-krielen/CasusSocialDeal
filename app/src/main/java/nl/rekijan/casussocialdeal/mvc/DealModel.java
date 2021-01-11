package nl.rekijan.casussocialdeal.mvc;

/**
 * Standard Model with fields and getters/setters with constructor
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 7-1-2021
 */
public class DealModel {

    private String uniqueId;
    private String imageLink;
    private String title;
    private String companyName;
    private String cityName;
    private String soldText;
    private double price;
    private double priceFrom;

    public DealModel(String uniqueId, String imageLink, String title, String companyName, String cityName, String soldText, double price, double priceFrom) {
        this.uniqueId = uniqueId;
        this.imageLink = imageLink;
        this.title = title;
        this.companyName = companyName;
        this.cityName = cityName;
        this.soldText = soldText;
        this.price = price;
        this.priceFrom = priceFrom;
    }

    //Getters and setters
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSoldText() {
        return soldText;
    }

    public void setSoldText(String soldText) {
        this.soldText = soldText;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }
}
