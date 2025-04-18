package internetShop.api.endpoint;

import internetShop.api.config.Config;

public class ProductEndpoints {
    public static final String BASE_URL_PRODUCT = Config.getProperty("api.base_url") + "/api/product";

    public static final String CREATE_PRODUCT = BASE_URL_PRODUCT + "/create";
    public static final String GET_IMAGE = BASE_URL_PRODUCT + "/get-image/{fileName}";
    public static final String GET_BY_ID = BASE_URL_PRODUCT + "/get-by-id";
    public static final String GET_ALL = BASE_URL_PRODUCT + "/get-all";
    public static final String DELETE_PRODUCT = BASE_URL_PRODUCT + "/delete";
}
