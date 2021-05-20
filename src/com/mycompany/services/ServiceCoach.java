/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Coach;

import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.Random;


/**
 *
 * @author masso
 */
public class ServiceCoach {

    public ArrayList<Coach> coaches;
    public Coach coach;
    public static ServiceCoach instance;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCoach() {
        req = new ConnectionRequest();
        coach = new Coach();
    }

    public static ServiceCoach getInstance() {
        if (instance == null) {
            instance = new ServiceCoach();
        }
        return instance;

    }
public boolean Addcoach(Coach c){
String url = Statics.BASE_URL + "/user/addUser?nom_co="+c.getNom_co()+"&prenom_co=" + c.getPrenom_co()+ "&genre_co=" + c.getGenre_co() + "&login_co="+c.getLogin_co()+"&mdp_co="+c.getMdp_co() ; 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;


}
 public ArrayList<Coach> parseComptes(String jsonText) {
        
     try {
            coaches = new ArrayList<>();
            
            JSONParser j = new JSONParser();

            Map<String, Object> comptesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) comptesListJson.get("root");// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        System.out.println("aaaa");
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Coach c = new Coach();
                
                
                c.setNom_co(obj.get("nomCo").toString());
                c.setPrenom_co(obj.get("prenomCo").toString());
                c.setGenre_co(obj.get("genreCo").toString());
                 c.setLogin_co(obj.get("loginCo").toString());
                  c.setMdp_co(obj.get("mdpCo").toString());
                coaches.add(c);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return coaches;
    }
 
 public ArrayList<Coach> getAllCoach() {
        String url = Statics.BASE_URL + "/liste";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                coaches = parseComptes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return coaches;
    }
 
  public boolean updateCoach(Coach c) {
        String url = Statics.BASE_URL + "/updateReclamation?id=" + c.getId_co()+"&nom="+c.getNom_co()+"&prenom=" + c.getPrenom_co()+ "&genre=" + c.getGenre_co()+ "&login="+c.getGenre_co()+"&mdp="+c.getMdp_co();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  public boolean deleteCoach(Coach c) {
        String url = Statics.BASE_URL + "/deleteReclamation?id=" + c.getId_co();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  
public boolean mdpoublier(Coach c) {
        String url = Statics.BASE_URL + "/user/email?username=" + c.getLogin_co();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


public Coach login(String username, String password) {
        String url = Statics.BASE_URL + "/user/signin?username=" + username + "&password=" + password;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                coach = findCompte(new String(req.getResponseData()));
                System.out.println(coach);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return coach;

    }

    public Coach findCompte(String jsonText) {

        try {
            
            JSONParser j = new JSONParser();
            j.setIncludeNulls(true);
            if(!jsonText.equals("null")){
                Map<String, Object> compteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
  
                
                if(compteListJson.containsKey("idCo"))
                    coach.setId_co((int) Float.parseFloat(compteListJson.get("idCo").toString()));
              
                if(compteListJson.containsKey("nomCo"))
                    coach.setNom_co(compteListJson.get("nomCo").toString());
                if(compteListJson.containsKey("prenomCo"))
                    coach.setPrenom_co(compteListJson.get("prenomCo").toString());
                if(compteListJson.containsKey("loginCo"))
                    coach.setLogin_co(compteListJson.get("loginCo").toString());
                if(compteListJson.containsKey("genreCo"))
                    coach.setGenre_co(compteListJson.get("genreCo").toString());
                if(compteListJson.containsKey("mdpCo"))
                    coach.setMdp_co(compteListJson.get("mdpCo").toString());

            }  
        } catch (IOException ex) {
            return coach;
        }
        return coach;
    }
    public boolean updateprofile(Coach c) {
        String url = Statics.BASE_URL + "/updatep?id=" + c.getId_co()+"&nom="+c.getNom_co()+"&prenom=" + c.getPrenom_co()+ "&genre=" + c.getGenre_co();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public void sendSms() {

        Twilio.init("AC5110b71cce19bab16cde98a018778611", "b48e2f174cc3773ef8db3653def1854d");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21626213651"),
                new com.twilio.type.PhoneNumber("+19282482909"),
                "159753")
                .create();

        System.out.println(message.getSid());
    }

}