package com.ipiecole.films.films;

public class FilmsData {
    private String categorie;
    private String description;
    private String titre;

    public FilmsData(String categorie, String description, String titre) {
        this.categorie = categorie;
        this.description = description;
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}


