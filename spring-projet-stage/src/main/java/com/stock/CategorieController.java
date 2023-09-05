package com.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {
    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @GetMapping
    public List<Categorie> listerCategories() {
        return categorieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> obtenirCategorieParId(@PathVariable Long id) {
        Optional<Categorie> categorie = categorieRepository.findById(id);
        if (categorie.isPresent()) {
            return ResponseEntity.ok(categorie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Categorie> creerCategorie(@RequestBody Categorie categorie) {
        Categorie categorieCreee = categorieRepository.save(categorie);
        return ResponseEntity.created(URI.create("/api/categories/" + categorieCreee.getId())).body(categorieCreee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> mettreAJourCategorie(@PathVariable Long id, @RequestBody Categorie categorieMaj) {
        Optional<Categorie> categorieExist = categorieRepository.findById(id);
        if (categorieExist.isPresent()) {
            Categorie categorie = categorieExist.get();

            // Mettre à jour les attributs spécifiés
            if (categorieMaj.getCode() != null) {
                categorie.setCode(categorieMaj.getCode());
            }
            if (categorieMaj.getLibelle() != null) {
                categorie.setLibelle(categorieMaj.getLibelle());
            }

            categorieRepository.save(categorie);
            return ResponseEntity.ok(categorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCategorie(@PathVariable Long id) {
        if (!categorieRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categorieRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
