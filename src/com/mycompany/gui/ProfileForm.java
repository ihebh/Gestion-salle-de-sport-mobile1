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
public class ProfileForm extends Form{

    public ProfileForm() {
        setTitle("Gestion de profile"); 
        
        TextField tfnom= new TextField("","Nom"); 
        TextField tfprenom= new TextField("","Prenom");
        ComboBox<List<String>> combo = new ComboBox<> (
          "Gymnastique","Cardio","Musculation"
          
        );
        
        Button btnValider = new Button("Add Coacht");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               Coach c= LoginForm.co  ;
               tfnom.setText(c.getNom_co());
               tfprenom.setText(c.getPrenom_co()) ;
               
                if( ServiceCoach.getInstance().updateprofile(c))
                     System.out.println("hello there");       
               
                   
            }
        });
        addAll(tfnom,tfprenom,combo,btnValider);
        
    
    
        

}
       
  
    
    
}
