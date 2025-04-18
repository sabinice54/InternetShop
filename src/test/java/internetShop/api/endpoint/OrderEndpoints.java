package internetShop.api.endpoint;

import internetShop.api.config.Config;

public class OrderEndpoints {
    public static final String BASE_URL_ORDER = Config.getProperty("api.base_url") + "/api/order";

    public static final String CREATE_ORDER = BASE_URL_ORDER + "/create";
    public static final String GET_ALL_ORDERS = BASE_URL_ORDER + "/get-all";
    public static final String CANCEL_ORDER_BY_ID = BASE_URL_ORDER + "/cancel-order";
    public static final String GET_ORDER_BY_ID = BASE_URL_ORDER + "/get-by-id";
}
