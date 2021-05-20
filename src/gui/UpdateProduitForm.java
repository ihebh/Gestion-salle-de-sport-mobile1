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

public class UpdateProduitForm extends Form {
    public void UpdateForm(Form previous) {
        setTitle("Modifier Produit");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField("","Nom");
        TextField tfmarque= new TextField("","Marque");
        TextField tfquantite = new TextField("","Quantité");
        TextField tfprix= new TextField("","Prix");
        TextField tfimage = new TextField("","Image");

        Button btnValider = new Button("Modifier");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Produit p= new Produit(tfnom.getText(), tfmarque.getText(), Integer.parseInt(tfquantite.getText()),
                        Float.parseFloat(tfprix.getText()),tfimage.getText(),0);
                if( ServiceProduit.getInstance().updateProduit(p))
                    System.out.println("Modification");


            }
        });


        addAll(tfnom,tfmarque,tfquantite,tfprix,tfimage,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
}
