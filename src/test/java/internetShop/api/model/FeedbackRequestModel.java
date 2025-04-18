package internetShop.api.model;

public record FeedbackRequestModel(
        int rating,
        String description
    ) {}
