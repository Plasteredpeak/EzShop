package Gui;

import Attributes.Admin;
import Attributes.Customer;
import Attributes.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener {

    JButton log,reg,back;
    JTextField n;
    JPasswordField p;

    String user;

    public Login(String name){
        user=name;
        setTitle(name);
        ImageIcon mainlogo=new ImageIcon("images\\mainlogo.png");
        setIconImage(mainlogo.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(300,340);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel head=new JPanel(null);
        head.setBounds(0,0,300,70);
        head.setBackground(new Color(52, 73, 94));
        JLabel heading=new JLabel("Login Credentials");
        heading.setBounds(45,0,300,70);
        heading.setFont(new Font("Verdana",Font.BOLD,20));
        heading.setForeground(Color.white);
        JLabel email=new JLabel("Login Credentials");
        email.setBounds(45,0,300,70);
        email.setFont(new Font("Verdana",Font.BOLD,20));
        email.setForeground(Color.white);

        head.add(heading);

        JPanel body=new JPanel(null);
        body.setBounds(0,70,300,270);
        body.setBackground(new Color(234, 250, 241));
        JLabel Uname=new JLabel("UserName:");
        Uname.setForeground(Color.black);
        Uname.setBounds(20,30,100,30);
        n=new JTextField();
        n.setBounds(110,30,150,30);
        n.setBackground(new Color(191, 201, 202 ));

        JLabel pass=new JLabel("Password:");
        pass.setForeground(Color.black);
        pass.setBounds(20,70,100,30);
        p=new JPasswordField();
        p.setBounds(110,70,150,30);
        p.setBackground(new Color(191, 201, 202 ));
        p.setEchoChar('*');

        log =new JButton("Login");
        log.setForeground(Color.white);
        log.setBackground(new Color(52, 73, 94));
        log.setBounds(30,130,100,40);
        log.setFocusable(false);
        log.setFont(new Font("Tahoma",Font.BOLD,15));
        log.addActionListener(this);

        reg =new JButton("Register");
        reg.setForeground(Color.white);
        reg.setBackground(new Color(175, 96, 26));
        reg.setBounds(150,130,100,40);
        reg.setFocusable(false);
        reg.setFont(new Font("Tahoma",Font.BOLD,15));
        reg.addActionListener(this);

        back =new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(new Color(203, 67, 53));
        back.setBounds(210,190,60,30);
        back.setFocusable(false);
        back.setFont(new Font("Tahoma",Font.PLAIN,12));
        back.addActionListener(e -> {new MainMenu();dispose();});


        body.add(Uname);
        body.add(n);
        body.add(p);
        body.add(pass);
        body.add(log);
        body.add(reg);
        body.add(back);

        setVisible(true);
        add(head);
        add(body);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==log){
            if(user.equalsIgnoreCase("administrator")) {
                File f=new File("Files\\Admin");
                if(f.exists()){
                Admin A = Admin.ReadFile().get(0);
                    if (A.getUsername().equals(n.getText())) {
                        if (A.getPassword().equals(String.valueOf(p.getPassword()))) {
                            new AdminMenu();
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Incorrect password!!"
                                    , "Error 101", JOptionPane.ERROR_MESSAGE);
                            p.setText("");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Incorrect Username!!"
                                , "Error 101", JOptionPane.ERROR_MESSAGE);
                        n.setText("");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Not Registered!!"
                            , "Error 101", JOptionPane.WARNING_MESSAGE);
                    n.setText("");
                    p.setText("");
                }

            }
            else if(user.equalsIgnoreCase("employee")) {
                File f = new File("Files\\Employee");
                if (f.exists()) {
                    ArrayList<Employee> E = Employee.ReadFile();
                    for (int i = 0; i < E.size(); i++) {
                        Employee emp = E.get(i);
                        if (emp.getUsername().equals(n.getText())) {
                            if (emp.getPassword().equals(String.valueOf(p.getPassword()))) {
                                new EmployeeMenu(i);
                                dispose();
                                return;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Incorrect UserName or password!!"
                            , "Error 101", JOptionPane.ERROR_MESSAGE);
                    n.setText("");
                    p.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, "No Employees Registered!!"
                            , "Error 101", JOptionPane.WARNING_MESSAGE);
                    n.setText("");
                    p.setText("");
                }

            }
            else{
                File f = new File("Files\\Customer");
                if (f.exists()) {
                    ArrayList<Customer> C = Customer.ReadFile();
                    for (int c=0;c<C.size();c++) {
                        if (C.get(c).getUsername().equals(n.getText())) {
                            if (C.get(c).getPassword().equals(String.valueOf(p.getPassword()))) {
                                new CustomerMenu(c);
                                dispose();
                                return;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Incorrect UserName or password!!"
                            , "Error 101", JOptionPane.ERROR_MESSAGE);
                    n.setText("");
                    p.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, "No Customers Registered!!"
                            , "Error 101", JOptionPane.WARNING_MESSAGE);
                    n.setText("");
                    p.setText("");
                }

            }
        }

        if(e.getSource()==reg){
            if(user.equalsIgnoreCase("administrator")){
                File f=new File("Files\\Admin");
                if(f.exists())
                    JOptionPane.showMessageDialog(null,"There can only be ONE !!! ","Admin already exits",JOptionPane.WARNING_MESSAGE);
                else{
                    new Register(n.getText(),String.valueOf(p.getPassword()),user);
                    dispose();
                }
            }
            else {
                new Register(n.getText(), String.valueOf(p.getPassword()), user);
                dispose();
            }
        }
    }

}
