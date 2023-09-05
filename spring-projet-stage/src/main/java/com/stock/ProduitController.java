package com.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final EtagereRepository etagereRepository;

    @Autowired
    public ProduitController(ProduitRepository produitRepository, CategorieRepository categorieRepository, EtagereRepository etagereRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.etagereRepository = etagereRepository;
    }

    @GetMapping
    public List<Produit> listerProduits() {
        return produitRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> obtenirProduitParId(@PathVariable Long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        if (produit.isPresent()) {
            return ResponseEntity.ok(produit.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Produit> creerProduit(@RequestBody Produit produit) {
        Produit produitCree = produitRepository.save(produit);
        return ResponseEntity.created(URI.create("/api/produits/" + produitCree.getId())).body(produitCree);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> mettreAJourProduit(@PathVariable Long id, @RequestBody Produit produitMaj) {
        Optional<Produit> produitExist = produitRepository.findById(id);
        if (produitExist.isPresent()) {
            Produit produit = produitExist.get();

            // Mettre à jour les attributs spécifiés
            if (produitMaj.getCode() != null) {
                produit.setCode(produitMaj.getCode());
            }
            if (produitMaj.getLibelle() != null) {
                produit.setLibelle(produitMaj.getLibelle());
            }
            if (produitMaj.getPrixUnitaire() != 0.0) {
                produit.setPrixUnitaire(produitMaj.getPrixUnitaire());
            }
            if (produitMaj.getCategorie() != null && produitMaj.getCategorie().getId() != null) {
                Optional<Categorie> categorieExist = categorieRepository.findById(produitMaj.getCategorie().getId());
                if (categorieExist.isPresent()) {
                    produit.setCategorie(categorieExist.get());
                } else {
                    return ResponseEntity.notFound().build(); // Réponse HTTP 404 Not Found
                }
            }
            if (produitMaj.getEtagere() != null && produitMaj.getEtagere().getId() != null) {
                Optional<Etagere> etagereExist = etagereRepository.findById(produitMaj.getEtagere().getId());
                if (etagereExist.isPresent()) {
                    produit.setEtagere(etagereExist.get());
                } else {
                    return ResponseEntity.notFound().build(); // Réponse HTTP 404 Not Found
                }
            }

            produitRepository.save(produit);
            return ResponseEntity.ok(produit);
        } else {
            return ResponseEntity.notFound().build(); // Réponse HTTP 404 Not Found
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable Long id) {
        if (!produitRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produitRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
