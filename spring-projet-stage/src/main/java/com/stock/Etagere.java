package com.stock;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Etagere {
    @Id
    @SequenceGenerator(
            name = "etagere_id_sequence",
            sequenceName = "etagere_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "etagere_id_sequence"
    )
    private Long id;

    private String numeroEtagere;

    public Etagere(Long id, String numeroEtagere) {
        this.id = id;
        this.numeroEtagere = numeroEtagere;
    }

    public Etagere() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroEtagere() {
        return numeroEtagere;
    }

    public void setNumeroEtagere(String numeroEtagere) {
        this.numeroEtagere = numeroEtagere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etagere etagere = (Etagere) o;
        return Objects.equals(id, etagere.id) && Objects.equals(numeroEtagere, etagere.numeroEtagere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroEtagere);
    }

    @Override
    public String toString() {
        return "Etagere{" +
                "id=" + id +
                ", numeroEtagere='" + numeroEtagere + '\'' +
                '}';
    }
}
