package internetShop.api.model;

public record ProductRequestModel(
                String name,
                String description,
                Double price,
                Integer count
        ) {}
