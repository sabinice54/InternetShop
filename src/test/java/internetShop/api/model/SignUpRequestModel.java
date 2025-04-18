package internetShop.api.model;

public record SignUpRequestModel(
        String fullName,
        String email,
        String password,
        String phoneNumber,
        double balance,
        String address
) {}