package com.example.ontapthtuan10_sqline1;

public class Author {
    private int id;
    private String name;
    private String address;
    private String email;

    public Author(int idAuthor, String name, String address, String email) {
        this.id = idAuthor;
        this.name = name;
        this.address = address;
        this.email = email;
    }
    public Author() {
        this.id = 0;
        this.name = null;
        this.address = null;
        this.email = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author{" +
                "idAuthor=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
