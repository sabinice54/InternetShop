package internetShop.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseModel {
    public Long id;
    public String fullName;
    public String email;
    public String phoneNumber;
    public String address;
    public Double balance;
    public String role;
}
