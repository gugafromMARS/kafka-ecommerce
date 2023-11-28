package gsc.projects.orderservice.model;

public enum ProductType {

    BOOKS("books"),
    COMPUTER("computer"),
    HOUSE("house"),
    HEALTH("health"),
    CLOTHES("clothes"),
    SPORTS("sports");

    private final String type;

    ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
