package ar.com.eventos.service.organizador;

import java.util.List;
import ar.com.eventos.domain.Chef;
import ar.com.eventos.domain.EventosGastronomicos;
import ar.com.eventos.domain.Participante;

public interface OrganizadorService {
    
    List<Chef> getChefs();

    List<EventosGastronomicos> getEventos();

    List<Participante> getParticipantesNuevos();

    void inicializarDatos();
}
