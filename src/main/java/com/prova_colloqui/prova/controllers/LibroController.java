package com.prova_colloqui.prova.controllers;

import com.prova_colloqui.prova.dto.request.LibroRequestDTO;
import com.prova_colloqui.prova.dto.response.LibroDTO;
import com.prova_colloqui.prova.entities.Libro;
import com.prova_colloqui.prova.services.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libri")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }


    @GetMapping
    public ResponseEntity<List<LibroDTO>> getTuttiLibri() {
        return ResponseEntity.ok(libroService.trovaTutti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> trovaPerId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.trovaPerId(id));
    }

    @PostMapping
    public ResponseEntity<LibroDTO> creaLibro(@RequestBody LibroRequestDTO dto) {
        LibroDTO libroCreato = libroService.saveLibro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroCreato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LibroDTO> modificaDisponibilita(@PathVariable Long id, @RequestParam boolean disponibilita) {
        return ResponseEntity.ok(libroService.modificaDisponibilita(id, disponibilita));
    }
 }
