package com.prova_colloqui.prova.services;

import com.prova_colloqui.prova.dto.request.LibroRequestDTO;
import com.prova_colloqui.prova.dto.response.LibroDTO;
import com.prova_colloqui.prova.entities.Autore;
import com.prova_colloqui.prova.entities.Libro;
import com.prova_colloqui.prova.exeption.ResourceNotFoundException;
import com.prova_colloqui.prova.repositories.AutoreRepository;
import com.prova_colloqui.prova.repositories.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutoreRepository autoreRepository;

    /**
     * L'iniezione di dipendenze tramite costruttore è la modalità consigliata poiché:
     * - Garantisce l'immutabilità dei dipendenti marcando i campi come 'final'.
     * - Migliora la testabilità consentendo l'iniezione di mock negli unit test senza framework.
     * - Previene problemi di riferimenti nulli (NullPointerException) all'istanziamento.
     */
    public LibroService(LibroRepository libroRepository, AutoreRepository autoreRepository) {
        this.libroRepository = libroRepository;
        this.autoreRepository = autoreRepository;
    }

    /**
     * Metodo creato per l'esercizio 2 non presente nel controller
     */
    public LibroDTO createLibro (Long id, Libro libro) {
        Autore autore = autoreRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));

        libro.setAutore(autore);

        Libro libroDB = libroRepository.save(libro);

        return LibroDTO.fromEntity(libroDB);
    }

    public List<LibroDTO> trovaTutti() {

        return libroRepository
                .findAll()
                .stream()
                .map(libro -> LibroDTO.fromEntity(libro))
                .toList();
    }

    public LibroDTO saveLibro (LibroRequestDTO libro) {
        Autore autore = autoreRepository
                .findById(libro.getAutoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Utente con id: %s, Non è stato trovato".formatted(libro.getAutoreId())));

        Libro libroDB = Libro.builder()
                .annoPubblicazione(libro.getAnnoPubblicazione())
                .disponibile(libro.isDisponibile())
                .autore(autore)
                .titolo(libro.getTitolo())
                .build();

        return LibroDTO.fromEntity(libroRepository.save(libroDB));
    }

    public LibroDTO trovaPerId(Long id) {
        Libro libroDB = libroRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro con id: %s, Non è stato trovato".formatted(id)));

        return LibroDTO.fromEntity(libroDB);
    }

    public void deleteById(Long id) {
        Libro libroDB = libroRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro con id: %s, Non è stato trovato".formatted(id)));
        libroRepository.deleteById(id);
    }

    public LibroDTO modificaDisponibilita(Long id, boolean disponibilita) {
        Libro libroDB = libroRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro con id: %s, Non è stato trovato".formatted(id)));

        libroDB.setDisponibile(disponibilita);

        return LibroDTO.fromEntity(libroDB);
    }
}
