package pl.javastart.equipy.equip.category;

import java.security.SecureRandom;

public class CatagoryDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
