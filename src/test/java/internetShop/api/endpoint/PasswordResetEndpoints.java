package internetShop.api.endpoint;

import internetShop.api.config.Config;

public class PasswordResetEndpoints {
    public static final String BASE_URL_RESET_PASSWORD = Config.getProperty("api.base_url") + "/api/reset";

    public static final String RESET_PASSWORD_BY_EMAIL = BASE_URL_RESET_PASSWORD;
}
