/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Coach;
import com.mycompany.services.ServiceCoach;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.concurrent.ThreadLocalRandom.current;
import javafx.scene.control.PasswordField;


/**
 *
 * @author masso
 */
public class LoginForm extends Form{

    public static Coach co ; 
    
    
    public LoginForm() {
        setTitle("Authentification"); 
        
        
        
        TextField tflogin= new TextField("","login",15,TextField.USERNAME);
        
        Button btnValider = new Button("Login");
        TextField erreur= new TextField("");
        TextField tfmdp = new TextField("","mdp",15,TextField.PASSWORD);
        
        CheckBox maskAndUnmaskCheck = new CheckBox();
        maskAndUnmaskCheck.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        if(maskAndUnmaskCheck.isSelected()){
           tfmdp.setConstraint(TextField.ANY); 
        } else {
            tfmdp.setConstraint(TextField.PASSWORD);
        }
        if(tfmdp.isEditing()) {
            tfmdp.stopEditing();
            tfmdp.startEditingAsync();
        } else {
            tfmdp.getParent().revalidate(); 
        }
    }
}); 
        btnValider.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent evt) {
               
               Coach result =ServiceCoach.getInstance().login(tflogin.getText(), tfmdp.getText()); 
               
               
                
               if(result.getId_co()==0)
               {
                   erreur.setText("the username or the password is incorrecte");
               }

               else
               {    co=result ; 
                   Toolbar.setGlobalToolbar(false);
               
                    Toolbar.setGlobalToolbar(true);
                    
                   
               }
               
                   
            }
        });
        addAll(tflogin,tfmdp,btnValider,maskAndUnmaskCheck,erreur);
        
    
    
        

}
       
  
    
    
}
