package com.prova_colloqui.prova.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean disponibile;
    private Integer annoPubblicazione;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

    private String titolo;



}
