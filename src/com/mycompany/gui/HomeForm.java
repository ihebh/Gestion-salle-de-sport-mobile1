/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author masso
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddCoach = new Button("Add Account");
        Button btnListCoaches = new Button("List Accounts");
        Button btnDeleteCoach = new Button("Delete Account");
        Button btnUpdateCoach = new Button("Update Account");
        Button btnPassword= new Button("forgot your password?");
        Button btnSms= new Button("sms");
        
        btnAddCoach.addActionListener(e-> new AddForm(current).show());
        btnListCoaches.addActionListener(e-> new ListForm(current).show());
        btnDeleteCoach.addActionListener(e-> new DeleteForm(current).show());
        btnUpdateCoach.addActionListener(e-> new UpdateForm(current).show());
        btnPassword.addActionListener(e-> new ForgotpwForm(current).show());
        btnPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                //ServiceCompte.getInstance().retrievePassword();
               
                   
            }
        });
        btnSms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               //ServiceCompte.getInstance().sendsms();
               
                   
            }
        });
        
        addAll(btnAddCoach,btnListCoaches,btnDeleteCoach,btnUpdateCoach,btnPassword,btnSms);
    }
    
    
}
