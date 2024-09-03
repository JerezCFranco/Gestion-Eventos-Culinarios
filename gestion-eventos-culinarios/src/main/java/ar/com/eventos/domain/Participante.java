package ar.com.eventos.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.com.eventos.enumeration.InteresesCulinariosEnum;

/*
 * Los participantes de estos eventos tendrán un identificador único, 
 * nombre y apellido, intereses culinarios (Comida italiana, mexicana, vegana, etc) 
 * y un historial de eventos a los que concurrió. 
 */

public class Participante {
    private UUID id;
    private String nombreYApellido;
    private List<InteresesCulinariosEnum> intereses = new ArrayList<>();
    private List<EventosGastronomicos> historialEventos = new ArrayList<>();

    public Participante(){}

    public Participante(UUID id, String nombreYApellido, List<InteresesCulinariosEnum> intereses) {
        this.id = id;
        this.nombreYApellido = nombreYApellido;
        this.intereses = intereses;
        this.historialEventos = new ArrayList<>();
    }


    public UUID getId() {
        return id;
    }
    public String getNombreYApellido() {
        return nombreYApellido;
    }
    public List<InteresesCulinariosEnum> getIntereses() {
        return intereses;
    }
    public List<EventosGastronomicos> getHistorialEventos() {
        return historialEventos;
    }
    public List<String> getNombresHistorialEventos() {
        List<String> nombresEventos = new ArrayList<>();
        for (EventosGastronomicos evento : historialEventos) {
            nombresEventos.add(evento.getNombre());
        }
        return nombresEventos;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }
    public void setIntereses(List<InteresesCulinariosEnum> intereses) {
        this.intereses = intereses;
    }
    public void setHistorialEventos(List<EventosGastronomicos> historialEventos) {
        this.historialEventos = historialEventos;
    }

    public void datosParticipante(){
        System.out.println("Id: " + getId() + "\nNombre y Apellido: " + getNombreYApellido() + "\nIntereses Culinarios: " + getIntereses() + "\nHistorial de Eventos: " + getNombresHistorialEventos());
    }
}
