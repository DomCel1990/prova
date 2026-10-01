package com.prova_colloqui.prova.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LibroRequestDTO {
    private String titolo;
    private Integer annoPubblicazione;
    private boolean disponibile;
    private Long autoreId;
}
