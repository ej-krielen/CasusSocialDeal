package nl.rekijan.casussocialdeal.utility;

/**
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 7-1-2021
 */
public class AppConstants {

    public static final String URL_PREFIX = "https://media.socialdeal.nl";
    public static final String DEAL_LIST_JSON = "https://media.socialdeal.nl/demo/deals.json";
    public static final String DEAL_DETAILS_LIST_JSON = "https://media.socialdeal.nl/demo/details.json?id=unique";

    //Json tags for the list of deals
    public static final String JSON_UNIQUE = "unique";
    public static final String JSON_IMG = "img";
    public static final String JSON_TITLE = "title";
    public static final String JSON_COMPANY_NAME = "company_name";
    public static final String JSON_CITY_NAME = "city_name";
    public static final String JSON_SOLD_TEXT = "sold_text";
    public static final String JSON_PRICE = "price";
    public static final String JSON_PRICE_FROM = "from";

    // Intent tags to transport data from list activity to detail activity
    public static final String INTENT_IMG = "INTENT_IMG";
    public static final String INTENT_TITLE = "INTENT_TITLE";
    public static final String INTENT_SUBTITLE = "INTENT_SUBTITLE";
    public static final String INTENT_COMPANY_NAME = "INTENT_COMPANY_NAME";
    public static final String INTENT_SOLD_TEXT = "INTENT_SOLD_TEXT";
    public static final String INTENT_PRICE = "INTENT_PRICE";
    public static final String INTENT_PRICE_FROM = "INTENT_PRICE_FROM";

    //Json tags for the details of a deal
    public static final String JSON_DESCRIPTION = "description";
}
