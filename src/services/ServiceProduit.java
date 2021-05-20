package services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import entities.Produit;
import utils.statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServiceProduit {

    public static ServiceProduit instance = null;
    private ConnectionRequest req ;
    public static boolean resultOK = true;

    public static ServiceProduit getInstance() {
        if(instance == null )
            instance = new ServiceProduit();
        return instance;
    }

    public ServiceProduit(){

        req = new ConnectionRequest();
    }

    public void ajouterProduit(Produit produit){

        String url = statics.BASE_URL+"/produit/m/addproduit?nom_produit="+produit.getNomProduit()+
                "&quantite="+produit.getQuantite()+"&marque_produit="+produit.getMarqueProduit()+
                "&prix="+produit.getPrix()+"&image_path="+produit.getImagePath();
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Produit> afficherProduits(){

        ArrayList<Produit> result = new ArrayList<>();
        String url = statics.BASE_URL+"/produit/m/displayProduits";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try{
                    Map<String, Object> mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapProduits.get("root");
                    System.out.println(listOfMaps);
                    for (Map<String, Object> obj : listOfMaps){

                        Produit p = new Produit();

                        int id = (int) Math.round((Double) (obj.get("idProduit")));

                        String nom = obj.get("nomProduit").toString();
                        String marque = obj.get("marqueProduit").toString();
                        int quantite = (int) Math.round((Double)(obj.get("quantite")));
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        String imagepath = obj.get("imagePath").toString();

                        p.setIdProduit(id);
                        p.setNomProduit(nom);
                        p.setMarqueProduit(marque);
                        p.setQuantite(quantite);
                        p.setPrix(prix);
                        p.setImagePath(imagepath);

                        result.add(p);

                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return result;
    }
    public Produit getOneProduit(String json) {
        Produit p = new Produit();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(obj);
            int id = (int) Math.round((Double) (obj.get("idProduit")));
            System.out.println(id);
            p.setIdProduit(id);
            p.setNomProduit(obj.get("nomProduit").toString());
            p.setMarqueProduit(obj.get("marqueProduit").toString());
            //p.setQuantite(Integer.parseInt(obj.get("quantite").toString()));
            p.setPrix(Float.parseFloat(obj.get("prix").toString()));
            p.setImagePath(obj.get("imagePath").toString());

        } catch (IOException ex) {
        }
        return p;

    }

    public Produit DetailsProduit (int id){

        Produit produit = new Produit();
        String url = statics.BASE_URL+"/produit/m/detailsproduit?id_produit="+id;
        req.setUrl(url);
        //String str = new String(req.getResponseData());
        req.addResponseListener((NetworkEvent evt) -> {
            ServiceProduit ser = new ServiceProduit();
            //JSONParser jsonp = new JSONParser();
            //try {
                //Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(new String(req.getResponseData()));

            produit.setIdProduit(ser.getOneProduit(new String(req.getResponseData())).getIdProduit());
            produit.setNomProduit(ser.getOneProduit(new String(req.getResponseData())).getNomProduit());
            produit.setMarqueProduit(ser.getOneProduit(new String(req.getResponseData())).getMarqueProduit());
            //produit.setQuantite(ser.getOneProduit(new String(req.getResponseData())).getQuantite());
            produit.setPrix(ser.getOneProduit(new String(req.getResponseData())).getPrix());
            produit.setImagePath(ser.getOneProduit(new String(req.getResponseData())).getImagePath());
        /*}catch (IOException ex ){
                System.out.println("SQL error"+ex.getMessage());
            }
            System.out.println("data == "+str);*/

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
    }
    public boolean deleteProduit(int id) {
        String url = statics.BASE_URL+ "/produit/m/deleteproduit?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean updateProduit(Produit p) {
        String url = statics.BASE_URL+ "/produit/m/updateproduit?id_produit="+p.getIdProduit()+
                "&nom_produit="+p.getNomProduit()+
                "&quantite="+p.getQuantite()+"&marque_produit="+p.getMarqueProduit()+
                "&prix="+p.getPrix()+"&image_path="+p.getImagePath();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
