package nl.rekijan.casussocialdeal.mvc;

/**
 * Started working on this but was already exceeding the time limit
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 9-1-2021
 */
public class DealDetailsModel {

    private String title;
    private String companyName;
    private String cityName;
    private String soldText;
    private double price;
    private double priceFrom;
    private double description;

    public DealDetailsModel(String title, String companyName, String cityName, String soldText, double price, double priceFrom, double description) {
        this.title = title;
        this.companyName = companyName;
        this.cityName = cityName;
        this.soldText = soldText;
        this.price = price;
        this.priceFrom = priceFrom;
        this.description = description;
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

    public double getDescription() {
        return description;
    }

    public void setDescription(double description) {
        this.description = description;
    }
}
