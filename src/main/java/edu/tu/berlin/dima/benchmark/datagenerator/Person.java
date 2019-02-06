package edu.tu.berlin.dima.benchmark.datagenerator;

public class Person {

    private long personId;
    private String name;
    private String email;
    private String creditCard;
    private String city;
    private String state;
    private long timestamp;

    public Person(long personId, String name, String email, String creditCard, String city, String state, long timestamp) {
        this.personId = personId;
        this.name = name;
        this.email = email;
        this.creditCard = creditCard;
        this.city = city;
        this.state = state;
        this.timestamp = timestamp;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
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

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return personId +
                "," + name+
                "," + email +
                "," + creditCard +
                "," + city  +
                "," + state +
                "," + timestamp;
    }
}
