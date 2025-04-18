package internetShop.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthApiResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("content")
    private AuthResponseModel content;

    public String getCode() {
        return code;
    }

    public AuthResponseModel getContent() {
        return content;
    }
}
