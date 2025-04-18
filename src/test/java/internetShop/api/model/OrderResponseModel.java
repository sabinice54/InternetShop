package internetShop.api.model;

public class OrderResponseModel {
    public String code;
    public Content content;

    public static class Content {
        public Long id;
        public Long productId;
        public String status;
    }
}
