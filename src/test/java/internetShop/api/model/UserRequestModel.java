package internetShop.api.model;

public record UserRequestModel(
        String fullName,
        String email,
        String password,
        String phoneNumber,
        double balance,
        String address
) {}
