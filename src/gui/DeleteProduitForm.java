package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Produit;
import services.ServiceProduit;

public class DeleteProduitForm extends Form {
    public void DeleteForm(Form previous) {

        setTitle("Supprimer Produit");
        setLayout(BoxLayout.y());


        TextField tfId= new TextField("","Id");
        Button btnValider = new Button("Supprimer Produit");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Produit p= new Produit(Integer.parseInt(tfId.getText()));
                if( ServiceProduit.getInstance().deleteProduit(p.getIdProduit()))
                    System.out.println("Suppression");


            }
        });


        addAll(tfId,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }

}
