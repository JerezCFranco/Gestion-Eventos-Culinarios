package ar.com.eventos.service.participante;


import java.util.UUID;

import ar.com.eventos.domain.Participante;

public interface ParticipanteService {
    
    Participante crearParticipante();

    Participante buscarParticipantePorId(UUID id);

    Participante buscarParticipanteEnOrganizadorPorId(UUID idParticipante);

    void listarParticipantes();
}
