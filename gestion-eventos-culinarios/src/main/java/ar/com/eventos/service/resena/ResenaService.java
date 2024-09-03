package ar.com.eventos.service.resena;

import java.util.UUID;

public interface ResenaService {
    
    void crearResena();

    void dejarResenaEnEvento(UUID idEvento, UUID idParticipante, int calificacion, String comentario);
}
