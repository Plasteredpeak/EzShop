package Attributes;

import java.io.*;
import java.util.ArrayList;

public class Queries implements Serializable {
    String user,Msg;

    public Queries(String user, String msg) {
        this.user = user;
        Msg = msg;
    }

    public String getUser() {
        return user;
    }

    public String getMsg() {
        return Msg;
    }
    public void  writeToFile(){
        ArrayList<Queries> list = ReadFile();
        list.add(this);
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Queries", false))){
            outStream.writeObject(list);
        }
        catch(IOException e){}

    }
    public static ArrayList<Queries> ReadFile(){
        ArrayList <Queries> list= new ArrayList<>();
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("Files\\Queries"));
            list=(ArrayList<Queries>) in.readObject();
            in.close();
        }

        catch (ClassNotFoundException | ClassCastException | IOException c){}

        return list;

    }
    public static void Delete(String u,String m){
        ArrayList<Queries> Q=ReadFile();
        for(Queries q:Q){
            if(q.getUser().equals(u) && q.getMsg().equals(m)){
                Q.remove(q);
                try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Queries", false))){
                    outStream.writeObject(Q);
                }
                catch(IOException exp){ }
                return;
            }
        }
    }
}
