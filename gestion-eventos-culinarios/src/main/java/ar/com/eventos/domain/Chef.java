package ar.com.eventos.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.com.eventos.enumeration.EspecialidadEnum;

// Los chefs por su parte se conoce que tienen un identificador único, 
// un nombre, una especialidad (El tipo de cocina por ejemplo Pastelería, 
// Cocina internacional, etc) y una lista de los eventos a los que participa. 


public class Chef {
    private UUID id;
    private String nombre;
    private EspecialidadEnum especialidad;
    private List<EventosGastronomicos> listaEventos;

    public Chef(){

    }

    public Chef(UUID id, String nombre, EspecialidadEnum especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.listaEventos = new ArrayList<>();
    }

    public String mostrarHistorialEventosConChef() {
        if (listaEventos.isEmpty()) {
            return "El chef " + nombre + " no ha participado en ningún evento.";
        }
    
        StringBuilder historial = new StringBuilder("del Chef " + nombre + ":\n");
    
        for (EventosGastronomicos evento : listaEventos) {
            historial.append("Evento: ").append(evento.getNombre()).append("\n");
            historial.append("Descripción: ").append(evento.getDescripcion()).append("\n");
            historial.append("Fecha y Hora: ").append(evento.getFechaYHora()).append("\n");
            historial.append("Ubicación: ").append(evento.getUbicacion()).append("\n");
        }
    
        return historial.toString();
    }
    
    public void verChef(){
        System.out.println("ID: " + getId() + "\nNombre: " + getNombre() + "\nEspecialidad: " + getEspecialidad() + "\nLista de Eventos " + mostrarHistorialEventosConChef());
    }

    public UUID getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public EspecialidadEnum getEspecialidad() {
        return especialidad;
    }
    public List<EventosGastronomicos> getListaEventos() {
        return listaEventos;
    }



    public void setId(UUID id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecialidad(EspecialidadEnum especialidad) {
        this.especialidad = especialidad;
    }
    public void setListaEventos(List<EventosGastronomicos> listaEventos) {
        this.listaEventos = listaEventos;
    }
}
