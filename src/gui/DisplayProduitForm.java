package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Produit;
import services.ServiceProduit;

import java.util.ArrayList;


public class DisplayProduitForm extends Form {
    Form current;
    final Button show = new Button("Show Dialog");
    final Button showPopup = new Button("Show Popup");
    public static Produit pubmap = null;

    public static int button_tomappub=0;

    public DisplayProduitForm(Resources res) {
        super("Liste des Produits", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Publications");
        getContentPane().setScrollVisible(false);
        /*tb.addSearchCommand(e -> {

        });*/
//super.addSideMenu(res);
        /*tb.addSearchCommand(e -> {
        });*/
        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe, s1, res.getImage("back-logo.jpeg"), "", "", res);

        //
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton PostsList = RadioButton.createToggle("Liste des Produits", barGroup);
        PostsList.setUIID("SelectBar");

        RadioButton addPost = RadioButton.createToggle("Ajouter un Produit", barGroup);
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


        ArrayList<Produit> postsliste = ServiceProduit.getInstance().afficherProduits();
        System.out.println(postsliste);
        for (int counter = 0; counter < postsliste.size(); counter++) {

            Label photopub = new Label();

            //String pathg = path + postsliste.get(counter).getUrl_pub();
            //System.out.println(path);
            Image placeholder = Image.createImage(1920, 1080, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            //photopub.setIcon(URLImage.createToStorage(encImage, "Medium_" + pathg, pathg, URLImage.RESIZE_SCALE));
            Image img = photopub.getIcon();

            Label whiteLine = new Label();
            whiteLine.setShowEvenIfBlank(true);
            whiteLine.getUnselectedStyle().setBgColor(0xff2c54);
            whiteLine.getUnselectedStyle().setBgTransparency(255);
            whiteLine.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
            whiteLine.getUnselectedStyle().setPadding(1, 0, 200, 1);
            Container cnt = new Container(new BorderLayout());
            /*if (img.getHeight() > Display.getInstance().getDisplayHeight()) {
                img = img.scaledHeight(400);
            }*/
            /*if (img.getWidth() > Display.getInstance().getDisplayWidth()) {
                img = img.scaledWidth(400);
            }*/
            Button btnmap = new Button("");
            String id_button = "" + postsliste.get(counter).getIdProduit();
            btnmap.setUIID(id_button);

            // System.out.println(piece_jointe);
            btnmap.addActionListener((e) -> {

                pubmap = ServiceProduit.getInstance().DetailsProduit(Integer.parseInt(btnmap.getUIID()));
                button_tomappub = Integer.parseInt(btnmap.getUIID());

                new DisplayProduitForm(res).show();
                refreshTheme();
            });

            Container cntImage = new Container();
            Container cntdesc = new Container();
            cntImage.add(img);
            String stringuewi2 = "\n \n" +"Marque du Produit:     "+postsliste.get(counter).getMarqueProduit()+ "\n \n" +"Nom du Produit:    "+ postsliste.get(counter).getNomProduit()+ "\n \n"+"Quantité du Produit:    " + postsliste.get(counter).getQuantite()+ "\n \n" +"Prix:    "+ postsliste.get(counter).getPrix()+" TND"+ "\n \n";
            Label l = new Label(postsliste.get(counter).getNomProduit() + "\n", "TextFieldRed");

            cntdesc.add(BoxLayout.encloseX(BoxLayout.encloseY(new SpanLabel(stringuewi2))));
            cnt.add(BorderLayout.NORTH, BoxLayout.encloseY(BoxLayout.encloseX(cntImage, cntdesc, btnmap)));

            cnt.add(BorderLayout.SOUTH, whiteLine);
            add(cnt);

//            cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(
//                    lId,lSupprimer,lModifier),BoxLayout.encloseX(lNom),BoxLayout.encloseX(lPrenom),BoxLayout.encloseX(lMail),BoxLayout.encloseX(lAge),BoxLayout.encloseX(lTel),BoxLayout.encloseX(lGenre),BoxLayout.encloseX(lDateNaiss),BoxLayout.encloseX(lUsername),BoxLayout.encloseX(lPassword),BoxLayout.encloseX(lRoke),BoxLayout.encloseX(lCreCompte),BoxLayout.encloseX(whiteLine)));
        }

    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String string2, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        /*if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }*/

        /*if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }*/

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                imageScale,
                overLay,
                BorderLayout.south(
                        BoxLayout.encloseY(
                                new SpanLabel(""),
                                spacer
                        )
                )
        );

        swipe.addTab("", res.getImage("back-logo.jpeg"), page1);
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