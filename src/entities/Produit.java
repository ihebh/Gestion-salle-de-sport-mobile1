package entities;

public class Produit {

    private int idProduit;
    private String nomProduit;
    private String marqueProduit;
    private int quantite;
    private float prix;
    private String imagePath;
    private int quantiteCommande;

    public Produit() {
    }
    public Produit(int id) {
        this.idProduit = id;
    }

    public Produit(String nomProduit, String marqueProduit, int quantite, float prix, String imagePath, int quantiteCommande) {
        this.nomProduit = nomProduit;
        this.marqueProduit = marqueProduit;
        this.quantite = quantite;
        this.prix = prix;
        this.imagePath = imagePath;
        this.quantiteCommande = quantiteCommande;
    }

    public Produit(int idProduit, String nomProduit, String marqueProduit, int quantite, float prix, String imagePath, int quantiteCommande) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.marqueProduit = marqueProduit;
        this.quantite = quantite;
        this.prix = prix;
        this.imagePath = imagePath;
        this.quantiteCommande = quantiteCommande;
    }



    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getMarqueProduit() {
        return marqueProduit;
    }

    public void setMarqueProduit(String marqueProduit) {
        this.marqueProduit = marqueProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getQuantiteCommande() {
        return quantiteCommande;
    }

    public void setQuantiteCommande(int quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

}
