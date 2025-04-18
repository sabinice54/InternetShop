package internetShop.api.endpoint;

import internetShop.api.config.Config;

public class AuthEndpoints {
    public static final String LOGIN = Config.getProperty("api.base_url") + "/api/auth/sign-in";
    public static final String SIGN_UP = Config.getProperty("api.base_url") + "/api/auth/sign-up";

}
