package ar.com.eventos.domain;

import java.util.UUID;

/*
 * Por último cada participante podrá dejar reseñas, 
 * cada reseña tendrá un identificador único, deberá referenciar a un evento, 
 * deberá referenciar al participante que dejó la reseña, una calificación (del 1 al 5)
 *  y por último un comentario.
 */

public class Resena {
    private UUID id;
    private EventosGastronomicos evento;
    private Participante participante;
    private int calificación;
    private String comentario;


    public String getNombreParticipante() {
        return participante != null ? participante.getNombreYApellido() : "Participante no asignado";
    }

    public UUID getId() {
        return id;
    }
    public EventosGastronomicos getEvento() {
        return evento;
    }
    public Participante getParticipante() {
        return participante;
    }
    public int getCalificación() {
        return calificación;
    }
    public String getComentario() {
        return comentario;
    }

    

    public void setId(UUID id) {
        this.id = id;
    }
    public void setEvento(EventosGastronomicos evento) {
        this.evento = evento;
    }
    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
    public void setCalificación(int calificación) {
        this.calificación = calificación;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
