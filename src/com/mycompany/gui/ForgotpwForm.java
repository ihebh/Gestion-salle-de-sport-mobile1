/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Coach;
import com.mycompany.services.ServiceCoach;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.PasswordField;


/**
 *
 * @author masso
 */
public class ForgotpwForm extends Form{

    public ForgotpwForm(Form previous) {
        setTitle("Forget Password"); 
        
        TextField tfnom= new TextField("","Username"); 
        
       
        
        Button btnValider = new Button("valid");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               Coach c= new Coach(tfnom.getText());
                if( ServiceCoach.getInstance().mdpoublier(c))
                     System.out.println("hello there");       
               
                   
            }
        });
        addAll(tfnom,btnValider);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack()) ; 
    
    
        

}
       
  
    
    
}
