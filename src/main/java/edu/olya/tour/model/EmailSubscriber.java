package edu.olya.tour.model;


import java.io.Serializable;

public class EmailSubscriber implements Serializable{
    private String name;
    private String email;

    public EmailSubscriber(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public EmailSubscriber() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailSubscriber that = (EmailSubscriber) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmailSubscriber{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
