package Attributes;

import java.io.*;
import java.util.ArrayList;

public class Order implements Serializable {
    String user,items;
    double Total;
    boolean status;

    public Order(String user, double total, String items) {
        this.user = user;
        Total = total;
        this.items = items;
        this.status = false;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void  writeToFile(){
        ArrayList<Order> list = ReadFile();
        list.add(this);
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Order", false))){
            outStream.writeObject(list);
        }
        catch(IOException e){}

    }
    public static ArrayList<Order> ReadFile(){
        ArrayList <Order> list= new ArrayList<>();
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("Files\\Order"));
            list=(ArrayList<Order>) in.readObject();
            in.close();
        }

        catch (ClassNotFoundException | ClassCastException | IOException c){}

        return list;

    }
}
