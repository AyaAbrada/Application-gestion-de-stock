package net.javaguides.usermanagement.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Produit {
    protected int id;
    protected String nom;
    protected String description;
    protected int quantite;
    protected double prix;
    protected String categorie;

    public Produit() {}

    public Produit(String nom, String description, int quantite, double prix, String categorie) {
        super();
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Produit(int id, String nom, String description, int quantite, double prix, String categorie) {
        super();
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public double getPrix() {
        return quantite;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setQuantite(String categorie) {
        this.categorie = categorie;
    }
}