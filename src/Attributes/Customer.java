package Attributes;

import java.io.*;
import java.util.ArrayList;

public class Customer extends Person implements Serializable {
    String username,password;

    public Customer(String f_name, String l_name, String age,
                 String phone_no, String email, String gender, Date dob, String username, String password) {
        super(f_name, l_name, age, phone_no, email, gender, dob);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void writeToFile(){
        ArrayList<Customer> list = ReadFile();
        list.add(this);
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Customer", false))){
            outStream.writeObject(list);
        }
        catch(IOException e){}

    }
    public static ArrayList<Customer> ReadFile(){
        ArrayList <Customer> list= new ArrayList<>();
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("Files\\Customer"));
            list=(ArrayList<Customer>) in.readObject();
            in.close();
        }

        catch (ClassNotFoundException | ClassCastException | IOException c){}

        return list;

    }
}
