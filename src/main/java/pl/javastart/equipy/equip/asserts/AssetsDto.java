package pl.javastart.equipy.equip.asserts;

import pl.javastart.equipy.equip.category.Category;

import javax.persistence.ManyToOne;

public class AssetsDto {
    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private String category;

    public AssetsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
