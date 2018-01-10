package selenium.java.lesson6.model;

public class Product {
    private boolean status;
    private String name;
    private String code;
    private String category;
    private String defaultCategory;
    private String group;
    private String groupValue;
    private String quantity;
    private String quantityUnit;
    private String deliveryStatus;
    private String soldOutStatus;
    private String dateFrom;
    private String dateTo;
    private String manufacturer;
    private String supplier;
    private String keywords;
    private String shortDescription;
    private String description;
    private String headTitle;
    private String metaDescription;
    private String price;
    private String currency;
    private String imagePath;

    public static Builder newEntity() { return new Product().new Builder(); }

    public boolean getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCategory() {
        return category;
    }

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public String getGroup() {
        return group;
    }

    public String getGroupValue() {
        return groupValue;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getSoldOutStatus() {
        return soldOutStatus;
    }

    public String getImagePath(){
        return imagePath;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public class Builder{

        public Builder setStatus(boolean status) {
            Product.this.status = status;
            return this;
        }

        public Builder setName(String name) {
            Product.this.name = name;
            return this;
        }

        public Builder setCode(String code) {
            Product.this.code = code;
            return this;
        }

        public Builder setCategory(String category) {
            Product.this.category = category;
            return this;
        }

        public Builder setDefaultCategory(String defaultCategory) {
            Product.this.defaultCategory = defaultCategory;
            return this;
        }

        public Builder setGroup(String group) {
            Product.this.group = group;
            return this;
        }

        public Builder setGroupValue(String groupValue) {
            Product.this.groupValue = groupValue;
            return this;
        }

        public Builder setQuantity(String quantity) {
            Product.this.quantity = quantity;
            return this;
        }

        public Builder setQuantityUnit(String quantityUnit) {
            Product.this.quantityUnit = quantityUnit;
            return this;
        }

        public Builder setDeliveryStatus(String deliveryStatus) {
            Product.this.deliveryStatus = deliveryStatus;
            return this;
        }

        public Builder setSoldOutStatus(String soldOutStatus) {
            Product.this.soldOutStatus = soldOutStatus;
            return this;
        }

        public Builder setDateFrom(String dateFrom) {
            Product.this.dateFrom = dateFrom;
            return this;
        }

        public Builder setDateTo(String dateTo) {
            Product.this.dateTo = dateTo;
            return this;
        }

        public Builder setImagePath(String imagePath){
            Product.this.imagePath = imagePath;
            return this;
        }

        public Builder setManufacturer(String manufacturer) {
            Product.this.manufacturer = manufacturer;
            return this;
        }

        public Builder setSupplier(String supplier) {
            Product.this.supplier = supplier;
            return this;
        }

        public Builder setKeywords(String keywords) {
            Product.this.keywords = keywords;
            return this;
        }

        public Builder setShortDescription(String shortDescription) {
            Product.this.shortDescription = shortDescription;
            return this;
        }

        public Builder setDescription(String description) {
            Product.this.description = description;
            return this;
        }

        public Builder setHeadTitle(String headTitle) {
            Product.this.headTitle = headTitle;
            return this;
        }

        public Builder setMetaDescription(String metaDescription) {
            Product.this.metaDescription = metaDescription;
            return this;
        }

        public Builder setPrice(String price) {
            Product.this.price = price;
            return this;
        }

        public Builder setCurrency(String currency) {
            Product.this.currency = currency;
            return this;
        }

        public Product build() {
            return Product.this;
        }
    }


}
