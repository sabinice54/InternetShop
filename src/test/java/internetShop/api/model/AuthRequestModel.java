package internetShop.api.model;

public record AuthRequestModel(
        String email,
        String password
) {
}
