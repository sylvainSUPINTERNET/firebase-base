package fr.supinternet.slike;

/**
 * Created by SYLVAIN on 22/09/2017.
 */

public class User {

    protected String name;
    protected String email;


    public User(String name, String email){
        this.name = name;
        this.email = email;
    }


    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
            this.email = email;
    }

}
