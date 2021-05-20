package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import entities.Produit;
import services.ServiceProduit;

public class AddProduitForm extends Form {

    Form current;

    public AddProduitForm(Resources res) {
        super("Ajouter Produit", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton PostsList = RadioButton.createToggle("Liste des Produits", barGroup);
        PostsList.setUIID("SelectBar");

        RadioButton addPost = RadioButton.createToggle(".", barGroup);
        addPost.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        PostsList.addActionListener((e) -> {

            DisplayProduitForm a = new DisplayProduitForm(res);
            a.show();
            refreshTheme();
        });
        addPost.addActionListener((e) -> {

            new AddProduitForm(res).show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, PostsList, addPost),
                FlowLayout.encloseBottom(arrow)
        ));

        PostsList.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(PostsList, arrow);
        });
        bindButtonSelection(PostsList, arrow);

        bindButtonSelection(addPost, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        TextField tfnom= new TextField("","Nom");
        TextField tfmarque= new TextField("","Marque");
        TextField tfquantite = new TextField("","Quantité");
        TextField tfprix= new TextField("","Prix");
        TextField tfimage = new TextField("","Image");

        Button btnValider = new Button("Ajouter");
        btnValider.addActionListener(evt -> {

            try {
                if ("".equals(tfnom.getText()) || "".equals(tfmarque.getText()) || "".equals(tfquantite.getText())
                        ||  "".equals(tfprix.getText() )||  "".equals(tfimage.getText())) {
                    Dialog.show("Veulliez vérifier les données", "", "Annuler", "OK");

                } else {
                    InfiniteProgress ip = new InfiniteProgress(); //loading start

                    final Dialog iDialog = ip.showInfiniteBlocking();


            Produit p= new Produit(tfnom.getText(), tfmarque.getText(), Integer.parseInt(tfquantite.getText()),
                    Float.parseFloat(tfprix.getText()),tfimage.getText(),0);
            System.out.println("data produit =="+p);
            ServiceProduit.getInstance().ajouterProduit(p);
                    iDialog.dispose(); //loading end
                    new DisplayProduitForm(res).show();
                    refreshTheme();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        addAll(tfnom,tfmarque,tfquantite,tfprix,tfimage,btnValider);




    }
    public void bindButtonSelection(Button btn, Label l) {

        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
    }
    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }
}
