package Gui;

import Attributes.Customer;
import Attributes.Order;
import Attributes.Products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CheckoutMenu extends JFrame implements ActionListener {
    String cat;
    double Total;
    ArrayList<Object[]> Cart;
    JButton del,Place;
    JTable Product;
    int index;
    CheckoutMenu(ArrayList<Object[]> C,double t,String c,int i) {
        cat = c;
        Cart=C;
        Total=t;
        index=i;
        setTitle("Chekout");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(700, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        ImageIcon mainlogo = new ImageIcon("images\\mainlogo.png");
        setIconImage(mainlogo.getImage());

        JPanel head = new JPanel();
        head.setBounds(0, 0, 700, 100);
        head.setBackground(new Color(44, 62, 80));
        head.setLayout(null);
        JLabel heading = new JLabel("Items in Cart:");
        heading.setFont(new Font("Futura", Font.BOLD, 35));
        heading.setBounds(30, 25, 400, 50);
        heading.setForeground(Color.white);
        head.add(heading);

        String[] col = {"No.", "Name", "Price (Rs.)", "Quantity", "Total"};

        DefaultTableModel Tb = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Product = new JTable(Tb);
        for (Object[] objects : Cart) {
            Tb.addRow(objects);
        }
        Product.setFont(new Font("Futura", Font.PLAIN, 14));
        Product.setRowHeight(30);
        TableColumnModel columnModel = Product.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(150);


        JScrollPane body = new JScrollPane(Product);
        body.setBounds(1, 100, 687, 300);
        body.setBackground(new Color(234, 250, 241));
        JPanel T=new JPanel(null);
        T.setBounds(0,400,700,100);
        T.setBackground(new Color(208, 211, 212));
        JLabel tname=new JLabel("Total:");
        tname.setForeground(new Color(52, 73, 94));
        tname.setFont(new Font("Rockwell",Font.BOLD,30));
        tname.setBounds(210,20,120,60);
        JLabel total=new JLabel(Total+" Rs.");
        total.setForeground(new Color(40, 180, 99));
        total.setFont(new Font("Myriad",Font.BOLD,30));
        total.setBounds(330,20,150,60);

        T.add(tname);
        T.add(total);



        JPanel footer = new JPanel();
        footer.setBounds(0, 500, 700, 100);
        footer.setBackground(new Color(86, 101, 115));
        footer.setLayout(null);

        JButton back = new JButton("Cancel");
        back.setForeground(Color.white);
        back.setBackground(new Color(203, 67, 53));
        back.setBounds(580, 20, 90, 30);
        back.setFocusable(false);
        back.setFont(new Font("Tahoma", Font.PLAIN, 12));
        back.addActionListener(E -> {
            int opt=JOptionPane.showConfirmDialog(null,"This will remove all items from your cart?","Warning",JOptionPane.OK_CANCEL_OPTION);
            if(opt==0) {
                new ShopMenu(cat, i);
                dispose();
            }
        });
        footer.add(back);

        del = new JButton("Remove");
        del.setForeground(Color.white);
        del.setBackground(new Color(230, 126, 34));
        del.setBounds(30, 20, 90, 30);
        del.setFocusable(false);
        del.setFont(new Font("Didot", Font.PLAIN, 12));
        del.addActionListener(this);
        footer.add(del);

        Place = new JButton("Place Order");
        Place.setForeground(Color.white);
        Place.setBackground(new Color(22, 160, 133));
        Place.setBounds(275, 10, 150, 40);
        Place.setFocusable(false);
        Place.setFont(new Font("Didot", Font.BOLD, 16));
        Place.addActionListener(this);
        footer.add(Place);


        setVisible(true);
        add(head);
        add(body);
        add(T);
        add(footer);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==del){
            int opt=JOptionPane.showConfirmDialog(null,"Remove Item from Cart?","Sure?",JOptionPane.YES_NO_OPTION);
            if(opt==0){
                int t= (int) Cart.get(Product.getSelectedRow())[4];
                Cart.remove(Product.getSelectedRow());
                Total-=t;
                if(Cart.isEmpty()){
                    new ShopMenu(cat, index);
                    dispose();
                }
                else {
                    new CheckoutMenu(Cart, Total, cat, index);
                    dispose();
                }
            }
        }
        if(e.getSource()==Place){
            int opt=JOptionPane.showConfirmDialog(null,"You want to Place Order?","Sure?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(opt==0){
                ArrayList<Products> P=Products.ReadFile(cat);
                String items="";
                for(Object[] i:Cart){
                    for(Products p:P){
                        if(p.getName().equals(i[1])){
                            p.setStock(p.getStock()-(int)i[3]);
                            break;
                        }
                    }
                    items+=i[1]+", ";
                }
                try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\"+cat, false))){
                    outStream.writeObject(P);
                }
                catch (IOException io){}

                ArrayList<Customer> C=Customer.ReadFile();
                Order O=new Order(C.get(index).getUsername(),Total,items);
                O.writeToFile();
                JOptionPane.showMessageDialog(null,"Order Placed Sucessfully","Mashallah",JOptionPane.INFORMATION_MESSAGE);
                new CustomerMenu(index);
                dispose();
            }
        }
    }
}
