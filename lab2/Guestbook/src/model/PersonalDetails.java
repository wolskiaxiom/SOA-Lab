package model;

public class PersonalDetails {
    public String login;
    public String pass;
    public String name;
    public String lastName;

    public PersonalDetails(String login, String pass, String name, String lastName) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.lastName = lastName;
    }
    public PersonalDetails(String login, String pass) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.lastName = lastName;
    }
}
