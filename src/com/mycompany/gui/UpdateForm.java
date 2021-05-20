/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Coach;
import com.mycompany.services.ServiceCoach;
import java.util.List;


/**
 *
 * @author masso
 */
public class UpdateForm extends Form{

    public UpdateForm(Form previous) {
    setTitle("Update coach");
        setLayout(BoxLayout.y());
        TextField tfid= new TextField("","Id"); 
         TextField tfnom= new TextField("","Nom"); 
        TextField tfprenom= new TextField("","Prenom");
        ComboBox<List<String>> combo = new ComboBox<> (
          "Gymnastique","Cardio","Musculation"
          
        );
        TextField tflogin= new TextField("","login");
        TextField tfmdp= new TextField("","mdp");
        Button btnValider = new Button("Add Coacht");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               Coach c= new Coach(Integer.parseInt(tfid.getText()),tfnom.getText(), tfprenom.getText(),combo.getSelectCommandText(), tflogin.getText(),  tfmdp.getText());
                if( ServiceCoach.getInstance().updateCoach(c))
                     System.out.println("hello there");       
               
                   
            }
        });
        
        
        addAll(tfid,tfnom,tfprenom,combo,tflogin,tfmdp,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    
}
