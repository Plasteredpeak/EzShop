package Attributes;
import java.io.*;
import java.util.ArrayList;

public class Products implements Serializable {
    String name,id,category,img;
    int price,stock;
    Date doe;
    public Products(String name, String id, int price, String category, int stock, Date doe,String img) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.doe = doe;
        this.img=img;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getDoe() {
        return doe;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void  writeToFile(){
        if(category.equalsIgnoreCase("beverages")){
            ArrayList<Products> list = ReadFile(category);
            list.add(this);
            try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Beverages", false))){
                outStream.writeObject(list);
            }
            catch(IOException e){}
        }
        else if(category.equalsIgnoreCase("Bakery Items")){
            ArrayList<Products> list = ReadFile(category);
            list.add(this);
            try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Bakery Items", false))){
                outStream.writeObject(list);
            }
            catch(IOException e){}
        }
        else if(category.equalsIgnoreCase("snacks")){
            ArrayList<Products> list = ReadFile(category);
            list.add(this);
            try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Snacks", false))){
                outStream.writeObject(list);
            }
            catch(IOException e){}
        }
        else if(category.equalsIgnoreCase("Fruits & Vegetables")){
            ArrayList<Products> list = ReadFile(category);
            list.add(this);
            try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Fruits & Vegetables", false))){
                outStream.writeObject(list);
            }
            catch(IOException e){}
        }


    }
    public static ArrayList<Products> ReadFile(String cat){
        ArrayList <Products> list= new ArrayList<>();
        if(cat.equalsIgnoreCase("beverages")){
            try{
                ObjectInputStream in=new ObjectInputStream(new FileInputStream("Files\\Beverages"));
                list=(ArrayList<Products>) in.readObject();
                in.close();
            }

            catch (ClassNotFoundException | ClassCastException | IOException c){}
        }
        else if(cat.equalsIgnoreCase("Bakery Items")){
            try{
                ObjectInputStream in=new ObjectInputStream(new FileInputStream("Files\\Bakery Items"));
                list=(ArrayList<Products>) in.readObject();
                in.close();
            }

            catch (ClassNotFoundException | ClassCastException | IOException c){
            }
        }
        else if(cat.equalsIgnoreCase("snacks")){
            try{
                ObjectInputStream in=new ObjectInputStream(new FileInputStream("Files\\Snacks"));
                list=(ArrayList<Products>) in.readObject();
                in.close();
            }

            catch (ClassNotFoundException | ClassCastException | IOException c){
            }
        }
        else if(cat.equalsIgnoreCase("Fruits & Vegetables")){
            try{
                ObjectInputStream in=new ObjectInputStream(new FileInputStream("Files\\Fruits & Vegetables"));
                list=(ArrayList<Products>) in.readObject();
                in.close();
            }

            catch (ClassNotFoundException | ClassCastException | IOException c){
            }
        }

        return list;
    }
}
