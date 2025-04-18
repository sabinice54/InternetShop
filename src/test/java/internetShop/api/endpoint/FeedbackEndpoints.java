package internetShop.api.endpoint;

import internetShop.api.config.Config;

public class FeedbackEndpoints {
    public static final String BASE_URL = Config.getProperty("api.base_url") + "/api/feedback";

    public static final String CREATE_FEEDBACK = BASE_URL + "/create";
    public static final String UPDATE_FEEDBACK = BASE_URL + "/update";
    public static final String GET_ALL_FEEDBACK = BASE_URL + "/get-all";
    public static final String GET_BY_ID_FEEDBACK = BASE_URL + "/get-by-id";
    public static final String DELETE_BY_ID_FEEDBACK = BASE_URL + "/delete-by-id";
}
