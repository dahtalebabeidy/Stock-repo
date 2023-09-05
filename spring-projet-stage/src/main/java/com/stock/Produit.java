package com.stock;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Produit {
    @Id
    @SequenceGenerator(
            name = "produit_id_sequence",
            sequenceName = "produit_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "produit_id_sequence"
    )
    private Long id;

    private String code;
    private String libelle;
    private double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "etagere_id")
    private Etagere etagere;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Produit(Long id, String code, String libelle, double prixUnitaire, Etagere etagere, Categorie categorie) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.prixUnitaire = prixUnitaire;
        this.etagere = etagere;
        this.categorie = categorie;
    }

    public Produit() {
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public Etagere getEtagere() {
        return etagere;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", etagere=" + etagere +
                ", categorie=" + categorie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return Double.compare(prixUnitaire, produit.prixUnitaire) == 0 && Objects.equals(id, produit.id) && Objects.equals(code, produit.code) && Objects.equals(libelle, produit.libelle) && Objects.equals(etagere, produit.etagere) && Objects.equals(categorie, produit.categorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, libelle, prixUnitaire, etagere, categorie);
    }

}

