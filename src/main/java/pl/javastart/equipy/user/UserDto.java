package pl.javastart.equipy.user;

import javax.persistence.Column;

public class UserDto {
    private Long id;
    private String firstName;
    private String LastName;
    private String pesel;

    public UserDto(Long id, String firstName, String lastName, String pesel) {
        this.id = id;
        this.firstName = firstName;
        LastName = lastName;
        this.pesel = pesel;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
