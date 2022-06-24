package Gui;

import Attributes.Customer;
import Attributes.Employee;
import Attributes.Queries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class EmployeeMenu extends JFrame implements ActionListener {
    ArrayList<Employee> E=Employee.ReadFile();
    JButton ShowO,showP,C,Q,R;
    int index;
    public EmployeeMenu(int i){
        index=i;
        setTitle(E.get(i).getUsername());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(700,600);
        setLayout(null);
        setLocationRelativeTo(null);
        ImageIcon mainlogo=new ImageIcon("images\\mainlogo.png");
        setIconImage(mainlogo.getImage());

        JPanel head=new JPanel(null);
        head.setBounds(0,0,700,100);
        head.setBackground(new Color(44, 62, 80));
        JLabel heading=new JLabel(E.get(i).getF_name()+" "+E.get(i).getL_name());
        heading.setFont(new Font("Rooney",Font.BOLD,40));
        heading.setBounds(15,25,300,50);
        heading.setForeground(Color.white);
        JLabel email=new JLabel(E.get(i).getEmail());
        email.setFont(new Font("Rooney",Font.PLAIN,16));
        email.setBounds(500,50,300,50);
        email.setForeground(Color.white);

        head.add(heading);
        head.add(email);


        JPanel body=new JPanel(null);
        body.setBounds(0,100,700,500);
        body.setBackground(new Color(213, 245, 227));

        ShowO=new JButton("Approve Orders");
        ShowO.setFocusable(false);
        ShowO.setBounds(250,50,200,65);
        ShowO.setFont(new Font("Bodoni",Font.BOLD,18));
        ShowO.setBackground(new Color(40, 55, 71));
        ShowO.setForeground(Color.white);
        ShowO.setBorder(BorderFactory.createEtchedBorder());
        ShowO.addActionListener(this);

        showP=new JButton("Manage Stock");
        showP.setFocusable(false);
        showP.setBounds(250,140,200,65);
        showP.setFont(new Font("Bodoni",Font.BOLD,18));
        showP.setBackground(new Color(40, 55, 71));
        showP.setForeground(Color.white);
        showP.setBorder(BorderFactory.createEtchedBorder());
        showP.addActionListener(this);

        C=new JButton("Complain");
        C.setFocusable(false);
        C.setBounds(250,230,200,65);
        C.setFont(new Font("Bodoni",Font.BOLD,18));
        C.setBackground(new Color(40, 55, 71));
        C.setForeground(Color.white);
        C.setBorder(BorderFactory.createEtchedBorder());
        C.addActionListener(this);

        Q=new JButton("Queries");
        Q.setFocusable(false);
        Q.setBounds(250,320,200,65);
        Q.setFont(new Font("Bodoni",Font.BOLD,18));
        Q.setBackground(new Color(40, 55, 71));
        Q.setForeground(Color.white);
        Q.setBorder(BorderFactory.createEtchedBorder());
        Q.addActionListener(this);


        JButton back =new JButton("Log Out");
        back.setForeground(Color.white);
        back.setBackground(new Color(203, 67, 53));
        back.setBounds(570,400,90,40);
        back.setFocusable(false);
        back.setFont(new Font("Tahoma",Font.BOLD,14));
        back.addActionListener(E -> {new MainMenu();dispose();});

        R =new JButton("Resign");
        R.setForeground(Color.white);
        R.setBackground(new Color(175, 96, 26));
        R.setBounds(30,400,100,40);
        R.setFocusable(false);
        R.setFont(new Font("Times New Roman",Font.BOLD,18));
        R.addActionListener(this);

        body.add(back);
        body.add(ShowO);
        body.add(showP);
        body.add(C);
        body.add(Q);
        body.add(R);

        setVisible(true);
        add(head);
        add(body);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ShowO){
            new ApproveOrderMenu(index);
            dispose();
        }
        if(e.getSource()==showP){
            String[] C={"Beverages","Bakery Items","Snacks","Fruits & Vegetables"};
            String opt=(String)JOptionPane.showInputDialog(null,"Pick a Category","Choose",JOptionPane.QUESTION_MESSAGE,null,C,C[0]);
            if(opt != null) {
                new ManageStock(opt,index);
                dispose();
            }
        }
        if(e.getSource()==Q){
            File f=new File("Files\\Queries");
            if(f.exists()) {
                ArrayList<Queries> Q = Queries.ReadFile();
                ArrayList<Customer> C = Customer.ReadFile();
                Object[] r = {"Close", "Delete", "Next"};
                Print:
                {
                    for (int i = 0; i < Q.size(); i++) {
                        for (int j = 0; j < C.size(); j++) {
                            if (Q.get(i).getUser().equals(C.get(j).getUsername())) {
                                int opt = JOptionPane.showOptionDialog(null, Q.get(i).getMsg(), C.get(j).getUsername(),
                                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, r, 0);
                                if (opt == 0 || opt == -1) {
                                    break Print;
                                } else if (opt == 1) {
                                    Queries.Delete(C.get(j).getUsername(), Q.get(i).getMsg());
                                    if (Queries.ReadFile().isEmpty()) {
                                        f.delete();
                                    }
                                    break;
                                }
                                else {
                                    break;
                                }

                            }
                        }
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(null,"No Queries Found","gg",JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource()==C){
            String msg=JOptionPane.showInputDialog(null,"Enter Message","Complain",JOptionPane.INFORMATION_MESSAGE);
            if(msg!=null){
                Queries Q=new Queries(E.get(index).getUsername(),msg);
                Q.writeToFile();
                JOptionPane.showMessageDialog(null,"Complain sent to Admin :)","Karen",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
