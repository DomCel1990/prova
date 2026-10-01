package com.prova_colloqui.prova.dto.response;

import com.prova_colloqui.prova.entities.Libro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LibroDTO {

    private String autore;
    private boolean disponibile;
    private Integer annoPubblicazione;
    private String titolo;

    public static LibroDTO fromEntity(Libro libro) {
        if (libro == null) return null;

        return LibroDTO.builder()
                .annoPubblicazione(libro.getAnnoPubblicazione())
                .autore(libro.getAutore() != null ? libro.getAutore().getName() : "Autore sconosciuto")
                .titolo(libro.getTitolo())
                .disponibile(libro.isDisponibile())
                .build();
    }
}
