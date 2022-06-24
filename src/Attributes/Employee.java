package Attributes;

import java.io.*;
import java.util.ArrayList;

public class Employee extends Person implements Serializable {
    String username,password;

    public Employee(String f_name, String l_name, String age,
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
        ArrayList<Employee> list = ReadFile();
        list.add(this);
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Employee", false))){
            outStream.writeObject(list);
        }
        catch(IOException e){}

    }
    public static ArrayList<Employee> ReadFile(){
        ArrayList <Employee> list= new ArrayList<>();
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("Files\\Employee"));
            list=(ArrayList<Employee>) in.readObject();
            in.close();
        }

        catch (ClassNotFoundException | ClassCastException | IOException c){}

        return list;

    }
}
