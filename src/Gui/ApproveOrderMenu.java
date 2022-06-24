package Gui;

import Attributes.Order;

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

public class ApproveOrderMenu extends JFrame implements ActionListener {

    JTable Product;
    JButton det,cancel;
    ArrayList<Order> O=Order.ReadFile();
    int index;
    ApproveOrderMenu(int i) {
        index=i;
        setTitle("Orders");
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
        JLabel heading = new JLabel("Placed Orders");
        heading.setFont(new Font("Futura", Font.BOLD, 35));
        heading.setBounds(30, 25, 400, 50);
        heading.setForeground(Color.white);
        head.add(heading);

        String[] col = {"No.", "Name", "Items", "Total (Rs.)", "Shipping Status"};

        DefaultTableModel Tb = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Product = new JTable(Tb);
        int no = 1;
        for (Order o : O) {
            if (!o.isStatus()) {
                Object[] obj = {no, "Order " + no++, o.getItems(), o.getTotal(), "Pending"};
                Tb.addRow(obj);
            }
        }
        Product.setFont(new Font("Futura", Font.PLAIN, 15));
        Product.setRowHeight(30);
        TableColumnModel columnModel = Product.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(400);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(100);


        JScrollPane body = new JScrollPane(Product);
        body.setBounds(1, 100, 687, 400);
        body.setBackground(new Color(234, 250, 241));


        JPanel footer = new JPanel();
        footer.setBounds(0, 500, 700, 400);
        footer.setBackground(new Color(93, 109, 126));
        footer.setLayout(null);

        JButton back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(new Color(203, 67, 53));
        back.setBounds(600, 20, 70, 30);
        back.setFocusable(false);
        back.setFont(new Font("Tahoma", Font.PLAIN, 12));
        back.addActionListener(E -> {
            new EmployeeMenu(i);
            dispose();
        });
        footer.add(back);

        det = new JButton("Details");
        det.setForeground(Color.white);
        det.setBackground(new Color(230, 126, 34));
        det.setBounds(30, 20, 70, 30);
        det.setFocusable(false);
        det.setFont(new Font("Didot", Font.PLAIN, 12));
        det.addActionListener(this);
        footer.add(det);

        cancel = new JButton("Approve Order");
        cancel.setForeground(Color.white);
        cancel.setBackground(new Color(22, 160, 133));
        cancel.setBounds(270, 10, 150, 40);
        cancel.setFocusable(false);
        cancel.setFont(new Font("Didot", Font.BOLD, 16));
        cancel.addActionListener(this);
        footer.add(cancel);


        setVisible(true);
        add(head);
        add(body);
        add(footer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==det){
            if(Product.getSelectedRow()!=-1){
                JOptionPane.showMessageDialog(null,Product.getModel().getValueAt(Product.getSelectedRow(),2),"ITEMS",JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null,"Select an Order to Show Details","Dumb 101",JOptionPane.ERROR_MESSAGE);
        }
        if(e.getSource()==cancel){
            if(Product.getSelectedRow()!=-1){
                int opt=JOptionPane.showConfirmDialog(null,"Ship Order?","Sure?",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(opt==0) {
                    int c = 0;
                    Order o = null;
                    for (int i = 0; c <= Product.getSelectedRow(); i++) {
                        if (!O.get(i).isStatus()) {
                            o = O.get(i);
                            c++;
                        }
                    }
                    o.setStatus(true);
                    try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Files\\Order", false))) {
                        outStream.writeObject(O);
                        JOptionPane.showMessageDialog(null, "Order Approved", "Shipped", JOptionPane.INFORMATION_MESSAGE);
                        new ApproveOrderMenu(index);
                        dispose();
                    }
                    catch (IOException exp) {
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(null,"Select an Order","Shipped",JOptionPane.WARNING_MESSAGE);
        }

    }
}
