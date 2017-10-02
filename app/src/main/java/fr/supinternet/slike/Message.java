package fr.supinternet.slike;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by SYLVAIN on 26/09/2017.
 */

public class Message {

    protected String message;
    protected String user;

    public Message(String message, String user) {
        this.message = message;
        this.user = user;
    }




    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (message != null ? !message.equals(message1.message) : message1.message != null)
            return false;
        return user != null ? user.equals(message1.user) : message1.user == null;

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage() {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}


