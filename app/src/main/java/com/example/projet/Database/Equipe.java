package com.example.projet.Database;

public class Equipe {
    private int id;
    private  String nom;
    private String Description;
    private String continent;

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
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Equipe(int id, String nom, String description, String continent) {
        this.id = id;
        this.nom = nom;
        Description = description;
        this.continent = continent;
    }

    public Equipe(String nom, String description, String continent) {
        this.nom = nom;
        Description = description;
        this.continent = continent;
    }
}
