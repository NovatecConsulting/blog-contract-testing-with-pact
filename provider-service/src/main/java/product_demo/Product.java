package product_demo;

/**
 * Created by AAT on 23.01.2017.
 */
public class Product {
    private final Integer id;
    private final String name;
    private String type;
    private String description;
    //TODO Check how to make pact test with a consumer that consumes only part of the json data

    public Product(Integer id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
