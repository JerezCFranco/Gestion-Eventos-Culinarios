package ar.com.eventos.service.participante.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import ar.com.eventos.domain.EventosGastronomicos;
import ar.com.eventos.domain.Organizador;
import ar.com.eventos.domain.Participante;
import ar.com.eventos.enumeration.InteresesCulinariosEnum;
import ar.com.eventos.service.participante.ParticipanteService;

public class ParticipanteServiceImpl implements ParticipanteService{

    private Organizador organizadorService;

    public ParticipanteServiceImpl(Organizador organizadorService){
        this.organizadorService = organizadorService;
    }

    @Override
    public Participante crearParticipante() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese el nombre y apellido del participante: ");
        String nombreYApellido = scan.nextLine();

        System.out.println("Seleccione los intereses culinarios del participante: ");
        InteresesCulinariosEnum[] intereses = InteresesCulinariosEnum.values();
        for (int i = 0; i < intereses.length; i++) {
            System.out.println((i + 1) + ". " + intereses[i]);
        }
        List<InteresesCulinariosEnum> interesesSeleccionados = new ArrayList<>();
        String seleccion = "";
        while (!seleccion.equalsIgnoreCase("n")) {
            System.out.println("Ingrese el número correspondiente al interés culinario(o 'n' para terminar): ");
            seleccion = scan.nextLine();
            if (!seleccion.equalsIgnoreCase("n")) {
                int index = Integer.parseInt(seleccion) - 1;
                if (index >= 0 && index < intereses.length) {
                    interesesSeleccionados.add(intereses[index]);
                } else {
                    System.out.println("Selección inválida, intente nuevamente.");
                }
            }
        }
        Participante nuevoParticipante = new Participante(UUID.randomUUID(), nombreYApellido, interesesSeleccionados);
        organizadorService.getParticipantesNuevos().add(nuevoParticipante);
        return nuevoParticipante;
    }

    @Override
    public void listarParticipantes() {
        if (organizadorService.getParticipantesNuevos().isEmpty()) {
            System.out.println("No hay participantes inscriptos.");
        } else {
            for (Participante participante : organizadorService.getParticipantesNuevos()) {
                participante.datosParticipante();
            }
        }
    }

    @Override
    public Participante buscarParticipantePorId(UUID id) {
        for (EventosGastronomicos evento : organizadorService.getEventos()) {
            Participante participante = evento.buscarParticipantePorId(id);
            if (participante != null) {
                return participante;
            }
        }
        return buscarParticipanteEnOrganizadorPorId(id);
    }

    @Override
    public Participante buscarParticipanteEnOrganizadorPorId(UUID idParticipante) {
        for (Participante participante : organizadorService.getParticipantesNuevos()) {
            if (participante.getId().equals(idParticipante)) {
                return participante;
            }
        }
        return null;
    }
    
}
