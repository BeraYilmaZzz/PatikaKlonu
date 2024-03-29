package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame{
    private JPanel panel1;
    private JTextField fld_register_name;
    private JTextField fld_register_uname;
    private JTextField fld_register_pass;
    private JButton btn_register;
    private JButton btn_register_logout;
    private JPanel wrapper;
    private JPanel wtop;

    public RegisterGUI(){
        add(wrapper);
        setSize(500,300);
        setLocation(Helper.screenCenterPoint("x",getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_register.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_register_name) || Helper.isFieldEmpty(fld_register_uname) || Helper.isFieldEmpty(fld_register_pass)){
                Helper.showMsg("fiil");
            }else {
                User findUser = new User();
                User.getFetch(findUser.getUname());
                if (findUser != null){
                    Helper.showMsg("Username already added! Please enter another username");
                }else {
                    User.add(fld_register_name.getText() , fld_register_uname.getText() , fld_register_pass.getText() , "student");
                    Helper.showMsg("done");
                }
            }
            LoginGUI loginGUI = new LoginGUI();
            dispose();
        });
        
        btn_register_logout.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });
        
    }
}
