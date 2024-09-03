package ar.com.eventos.domain;

import java.util.ArrayList;
import java.util.List;

public class Organizador {
    private List<EventosGastronomicos> eventos = new ArrayList<>();
    private List<Chef> chefs = new ArrayList<>();
    private List<Participante> participantesNuevos = new ArrayList<>();

    public List<Chef> getChefs() {
        return chefs;
    }
    public List<EventosGastronomicos> getEventos() {
        return eventos;
    }
    public List<Participante> getParticipantesNuevos() {
        return participantesNuevos;
    }

}
