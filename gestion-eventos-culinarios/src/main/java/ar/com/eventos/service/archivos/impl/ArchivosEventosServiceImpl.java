package ar.com.eventos.service.archivos.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ar.com.eventos.domain.EventosGastronomicos;
import ar.com.eventos.domain.Participante;
import ar.com.eventos.domain.Resena;
import ar.com.eventos.service.archivos.ArchivosEventosService;
import ar.com.eventos.service.organizador.OrganizadorService;

public class ArchivosEventosServiceImpl implements ArchivosEventosService{

    private OrganizadorService organizadorService;

    public ArchivosEventosServiceImpl(OrganizadorService organizadorService){
        this.organizadorService = organizadorService;
    }

    @Override
    public void exportarEventosCapacidadMaxima(LocalDate fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nombreArchivo = "Eventos_con_capacidad_maxima_" + fecha.format(formatter) + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (EventosGastronomicos evento : organizadorService.getEventos()) {
                if (evento.getCapacidad() == evento.getParticipantes().size() &&
                        evento.getFechaYHora().toLocalDate().equals(fecha)) {
                    writer.write("ID: " + evento.getId());
                    writer.write("\nNombre: " + evento.getNombre());
                    writer.write("\nDescripción: " + evento.getDescripcion());
                    writer.write("\nFecha y Hora: " + evento.getFechaYHora());
                    writer.write("\nUbicación: " + evento.getUbicacion());
                    writer.write("\nCapacidad: " + evento.getCapacidad() + " participante/s");
                    writer.write("\nChef a Cargo: "
                            + (evento.getChefCargo() != null ? evento.getChefCargo().getNombre() : "No asignado"));
                    writer.write("\nParticipantes:");
                    for (Participante participante : evento.getParticipantes()) {
                        writer.write("\n- Participante ID: " + participante.getId() + ", Nombre: "
                                + participante.getNombreYApellido());
                    }

                    writer.write("\nReseñas:");
                    for (Resena resena : evento.getResenas()) {
                        writer.write("\n- Participante ID: " + resena.getId() + ", Nombre y Apellido: " + resena.getNombreParticipante() +", Calificación: "
                                + resena.getCalificación() + " estrellas, Comentario: " + resena.getComentario());
                    }
                    writer.write("\n------------------------------\n");
                }
            }
            System.out.println("Archivo exportado exitosamente: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
    
}
