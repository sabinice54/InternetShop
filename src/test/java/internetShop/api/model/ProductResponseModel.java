// File: kg/nurtelecom/model/ProductResponseModel.java
package internetShop.api.model;

public class ProductResponseModel {
    public String code;
    public Content content;

    public static class Content {
        public Long id;
        public String name;
        public Double price;
        public String description;
        public Integer count;
        public String imagePath;
    }
}
