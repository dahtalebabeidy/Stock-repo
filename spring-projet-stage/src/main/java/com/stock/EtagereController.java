package com.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etageres")
public class EtagereController {
    private final EtagereRepository etagereRepository;

    @Autowired
    public EtagereController(EtagereRepository etagereRepository) {
        this.etagereRepository = etagereRepository;
    }

    @GetMapping
    public List<Etagere> listerEtagères() {
        return etagereRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etagere> obtenirEtagereParId(@PathVariable Long id) {
        Optional<Etagere> etagere = etagereRepository.findById(id);
        if (etagere.isPresent()) {
            return ResponseEntity.ok(etagere.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Etagere> creerEtagere(@RequestBody Etagere etagere) {
        Etagere etagereCreee = etagereRepository.save(etagere);
        return ResponseEntity.created(URI.create("/api/etageres/" + etagereCreee.getId())).body(etagereCreee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etagere> mettreAJourEtagere(@PathVariable Long id, @RequestBody Etagere etagereMaj) {
        Optional<Etagere> etagereExist = etagereRepository.findById(id);
        if (etagereExist.isPresent()) {
            Etagere etagere = etagereExist.get();

            // Mettre à jour les attributs spécifiés
            if (etagereMaj.getNumeroEtagere() != null) {
                etagere.setNumeroEtagere(etagereMaj.getNumeroEtagere());
            }

            etagereRepository.save(etagere);
            return ResponseEntity.ok(etagere);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEtagere(@PathVariable Long id) {
        if (!etagereRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        etagereRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
