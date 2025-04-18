package internetShop.api.endpoint;

import internetShop.api.config.Config;

public class UserEndpoints {
    public static final String BASE_URL_USER = Config.getProperty("api.base_url") + "/api/user";

    public static final String UPDATE_USER = BASE_URL_USER + "/update";
    public static final String GET_ALL_USERS = BASE_URL_USER + "/get-all";
    public static final String GET_USER_BY_ID = BASE_URL_USER + "/by-id";
    public static final String DELETE_USER_BY_ID = BASE_URL_USER + "/delete-by-id";
}
