package ar.com.eventos.service.resena.impl;

import java.util.Scanner;
import java.util.UUID;

import ar.com.eventos.domain.EventosGastronomicos;
import ar.com.eventos.domain.Participante;
import ar.com.eventos.service.eventos.EventosGastronomicosService;
import ar.com.eventos.service.participante.ParticipanteService;
import ar.com.eventos.service.resena.ResenaService;

public class ResenaServiceImpl implements ResenaService{

    private EventosGastronomicosService eventosGastronomicosService;
    private ParticipanteService participanteService;

    public ResenaServiceImpl(EventosGastronomicosService eventosGastronomicosService, ParticipanteService participanteService){
        this.eventosGastronomicosService = eventosGastronomicosService;
        this.participanteService = participanteService;
    }

    @Override
    public void crearResena() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el id del evento: ");
        String idDelEventoStr = scan.nextLine();
        UUID idDelEvento;
        try {
            idDelEvento = UUID.fromString(idDelEventoStr);
        } catch (IllegalArgumentException e) {
            System.out.println("El ID del evento ingresado no es un UUID válido.");
            return;
        }

        System.out.println("Ingrese el id del participante: ");
        String idDelParticipanteStr = scan.nextLine();
        UUID idDelParticipante;
        try {
            idDelParticipante = UUID.fromString(idDelParticipanteStr);
        } catch (IllegalArgumentException e) {
            System.out.println("El ID del participante ingresado no es un UUID válido.");
            return;
        }

        System.out.println("Ingrese la calificación que le da al evento(1 a 5):");
        int calificacionDelEvento = scan.nextInt();
        scan.nextLine();
        if (calificacionDelEvento < 1 || calificacionDelEvento > 5) {
            System.out.println("No puede agregar una reseña inferior a 1, o mayor a 5");
            return;
        }
        System.out.println("Ingrese algún comentario sobre el evento: ");
        String comentarioDelEvento = scan.nextLine();

        dejarResenaEnEvento(idDelEvento, idDelParticipante, calificacionDelEvento, comentarioDelEvento);
    }

    @Override
    public void dejarResenaEnEvento(UUID idEvento, UUID idParticipante, int calificacion, String comentario) {
        EventosGastronomicos evento = eventosGastronomicosService.buscarEventoPorId(idEvento);
        Participante participante = participanteService.buscarParticipantePorId(idParticipante);

        if (evento != null && participante != null) {
            evento.dejarResena(participante, calificacion, comentario);
        } else {
            System.out.println("Evento o participante no encontrado.");
        }
    }
    
}
