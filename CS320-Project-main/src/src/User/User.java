package User;
import java.util.*;
public class User {
    private String name;
    private String password;

    public User(String name,String password){
        this.name = name;
        this.password = password;
    }
    public String getName(){
        return name;
    }
}
