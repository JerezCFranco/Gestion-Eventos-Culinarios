package ar.com.eventos.service.eventos.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

import ar.com.eventos.domain.Chef;
import ar.com.eventos.domain.EventosGastronomicos;
import ar.com.eventos.domain.Participante;
import ar.com.eventos.service.chef.ChefService;
import ar.com.eventos.service.eventos.EventosGastronomicosService;
import ar.com.eventos.service.organizador.OrganizadorService;
import ar.com.eventos.service.participante.ParticipanteService;

public class EventosGastronomicosServiceImpl implements EventosGastronomicosService{

    private OrganizadorService organizadorService;
    private ChefService chefService;
    private ParticipanteService participanteService;

    public EventosGastronomicosServiceImpl(ChefService chefService,OrganizadorService organizadorService, ParticipanteService participanteService){
        this.chefService = chefService;
        this.organizadorService = organizadorService;
        this.participanteService = participanteService;
    }

    @Override
    public EventosGastronomicos crearEvento() {
        EventosGastronomicos nuevoEvento = new EventosGastronomicos();
        Scanner scan = new Scanner(System.in);

        nuevoEvento.setId(UUID.randomUUID());

        System.out.println("Ingrese el nombre del evento: ");
        String nombreEvento = scan.nextLine();
        nuevoEvento.setNombre(nombreEvento);

        System.out.println("Ingrese la descripcion del evento: ");
        String descripcionEvento = scan.nextLine();
        nuevoEvento.setDescripcion(descripcionEvento);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Ingrese fecha y hora en el formato dd/MM/yyyy HH:mm:");
        String fechaHoraIngresada = scan.nextLine();
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraIngresada, inputFormatter);
        nuevoEvento.setFechaYHora(fechaHora);

        System.out.println("Ingrese la ubicacion del evento: ");
        String ubicacionEvento = scan.nextLine();
        nuevoEvento.setUbicacion(ubicacionEvento);

        System.out.println("Ingrese la capacidad maxima de participantes en el evento: ");
        int capacidadEvento = scan.nextInt();
        scan.nextLine();
        nuevoEvento.setCapacidad(capacidadEvento);

        System.out.println("¿Desea asignar un chef existente o crear uno nuevo? (1- Existente, 2- Nuevo)");
        int eleccion = scan.nextInt();
        scan.nextLine();
        Chef chefAsignado = null;

        if (eleccion == 1) {
            System.out.println("Ingrese el nombre del chef a asignar: ");
            String nombreChef = scan.nextLine();
            for (Chef chef : organizadorService.getChefs()) {
                if (chef.getNombre().equalsIgnoreCase(nombreChef)) {
                    chefAsignado = chef;
                    break;
                }
            }
            if (chefAsignado == null) {
                System.out.println("No se encontró un chef con ese nombre.");
            }
        } else if (eleccion == 2) {
            chefAsignado = chefService.crearChef();
        }

        if (chefAsignado != null) {
            nuevoEvento.setChefCargo(chefAsignado);
            chefAsignado.getListaEventos().add(nuevoEvento);
        }

        organizadorService.getEventos().add(nuevoEvento);

        return nuevoEvento;
    }

    @Override
    public void verEventos() {
        if (organizadorService.getEventos().isEmpty()) {
            System.out.println("No hay eventos registrados.");
        } else {
            for (EventosGastronomicos evento : organizadorService.getEventos()) {
                evento.datosEvento();
            }
        }
    }

    @Override
    public void inscribirParticipanteEnEvento(UUID idEvento, UUID idParticipante) {
        EventosGastronomicos evento = buscarEventoPorId(idEvento);
        Participante participante = participanteService.buscarParticipantePorId(idParticipante);

        if (evento != null && participante != null) {
            boolean exito = evento.inscribirParticipante(participante);
            if (exito) {
                System.out.println("Inscripcion realizada exitosamente.");
            }
        } else {
            System.out.println("Evento o participante no encontrado.");
        }
    }

    @Override
    public void listarParticipantesDeEvento(UUID idEvento) {
        EventosGastronomicos evento = buscarEventoPorId(idEvento);
        if (evento != null) {
            evento.mostrarParticipantes();
        } else {
            System.out.println("Evento no encontrado.");
        }
    }

    @Override
    public void asignarChefAEvento(UUID idEvento, UUID idChef) {
        EventosGastronomicos evento = buscarEventoPorId(idEvento);
        Chef chef = chefService.buscarChef(idChef);

        if (evento != null && chef != null) {
            evento.setChefCargo(chef);
            if (!chef.getListaEventos().contains(evento)) {
                chef.getListaEventos().add(evento);
            }
            System.out.println("Chef asignado con éxito al evento.");
        } else {
            System.out.println("Evento o chef no encontrado.");
        }
    }

    @Override
    public EventosGastronomicos buscarEventoPorId(UUID id) {
        for (EventosGastronomicos evento : organizadorService.getEventos()) {
            if (evento.getId().equals(id)) {
                return evento;
            }
        }
        return null;
    }

    @Override
    public Participante buscarParticipantePorIdYEvento(UUID idEvento, UUID idParticipante) {
        EventosGastronomicos evento = buscarEventoPorId(idEvento);
        if (evento != null) {
            return evento.buscarParticipantePorId(idParticipante);
        }
        return null;
    }

}
