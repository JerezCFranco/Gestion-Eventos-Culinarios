package ar.com.eventos.service.eventos;

import java.util.UUID;

import ar.com.eventos.domain.EventosGastronomicos;
import ar.com.eventos.domain.Participante;

public interface EventosGastronomicosService {

    EventosGastronomicos crearEvento();

    void verEventos();
    
    void inscribirParticipanteEnEvento(UUID idEvento, UUID idParticipante);

    void listarParticipantesDeEvento(UUID idEvento);

    Participante buscarParticipantePorIdYEvento(UUID idEvento, UUID idParticipante);

    void asignarChefAEvento(UUID idEvento, UUID idChef);

    EventosGastronomicos buscarEventoPorId(UUID id);

    
}
