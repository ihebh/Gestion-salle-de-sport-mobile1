/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Coach;
import com.mycompany.services.ServiceCoach;



/**
 *
 * @author masso
 */
public class DeleteForm extends Form{

    public DeleteForm(Form previous) {
        
        setTitle("Delete an account");
        setLayout(BoxLayout.y());
        
       
        TextField tfId= new TextField("","Id");
        Button btnValider = new Button("Delete compte");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               Coach c= new Coach( Integer.parseInt(tfId.getText()));
                if( ServiceCoach.getInstance().deleteCoach(c))
                     System.out.println("hello there");       
               
                   
            }
        });
        
        
        addAll(tfId,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    
    
}
