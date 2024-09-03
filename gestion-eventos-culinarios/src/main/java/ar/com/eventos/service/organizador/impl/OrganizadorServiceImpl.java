package ar.com.eventos.service.organizador.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ar.com.eventos.domain.Chef;
import ar.com.eventos.domain.EventosGastronomicos;
import ar.com.eventos.domain.Organizador;
import ar.com.eventos.domain.Participante;
import ar.com.eventos.domain.Resena;
import ar.com.eventos.enumeration.EspecialidadEnum;
import ar.com.eventos.enumeration.InteresesCulinariosEnum;
import ar.com.eventos.service.organizador.OrganizadorService;

public class OrganizadorServiceImpl implements OrganizadorService{

    Organizador organizador;

    public OrganizadorServiceImpl(Organizador organizador){
        this.organizador = organizador;
    }

    @Override
    public List<Chef> getChefs() {
        return this.organizador.getChefs();
    }

    @Override
    public List<EventosGastronomicos> getEventos() {
        return this.organizador.getEventos();
    }

    @Override
    public List<Participante> getParticipantesNuevos() {
        return this.organizador.getParticipantesNuevos();
    }

    @Override
    public void inicializarDatos() {
        // Crear chefs
        Chef chef1 = new Chef(UUID.fromString("baf5d23e-fb9e-4a7b-a0b2-c6eae6c00000"), "Omarcito", EspecialidadEnum.PARRILLA);
        Chef chef2 = new Chef(UUID.fromString("c8a6ed95-3c7d-4b55-b9b5-76f4b22ff700"), "Ana María", EspecialidadEnum.GOURMET);
        Chef chef3 = new Chef(UUID.fromString("d3c4c8d8-9c48-48ec-97ae-7d1e51b0a9a8"), "Roberto Asador", EspecialidadEnum.ASADOS);
        Chef chef4 = new Chef(UUID.fromString("e7d60d8b-fd4a-4d5e-89a0-1e89b203a0d4"), "Isabel Dulce", EspecialidadEnum.REPOSTERIA);
        Chef chef5 = new Chef(UUID.fromString("f7b826d5-0b5d-4f38-bdb0-9a7734e6c5b7"), "Javier Vino", EspecialidadEnum.INTERNACIONAL);

        organizador.getChefs().add(chef1);
        organizador.getChefs().add(chef2);
        organizador.getChefs().add(chef3);
        organizador.getChefs().add(chef4);
        organizador.getChefs().add(chef5);

        // Crear participantes
        Participante participante1 = new Participante(UUID.fromString("301c5a0c-d791-4259-8c20-f777c8053d82"), "Carlos Jerez", List.of(InteresesCulinariosEnum.ITALIANA));
        Participante participante2 = new Participante(UUID.fromString("a7c88b71-56b5-4ef6-8a14-c2147b19d5a7"), "Mariana López", List.of(InteresesCulinariosEnum.MEDITERRANEA));
        Participante participante3 = new Participante(UUID.fromString("7f1d25e1-3e8b-42d1-95b1-d7d7b2a53a43"), "Juan Pérez", List.of(InteresesCulinariosEnum.VEGANA, InteresesCulinariosEnum.ASIATICA));
        Participante participante4 = new Participante(UUID.fromString("22b8b6b0-18d4-4c1e-9e35-bc1c91e67f93"), "Gabriela Martínez", List.of(InteresesCulinariosEnum.FRANCESA));
        Participante participante5 = new Participante(UUID.fromString("3e9d84b7-0d72-4e51-9a60-9e8d1b3f9d5f"), "Sergio López", List.of(InteresesCulinariosEnum.MEXICANA));

        organizador.getParticipantesNuevos().add(participante1);
        organizador.getParticipantesNuevos().add(participante2);
        organizador.getParticipantesNuevos().add(participante3);
        organizador.getParticipantesNuevos().add(participante4);
        organizador.getParticipantesNuevos().add(participante5);

        // Crear eventos
        EventosGastronomicos evento1 = new EventosGastronomicos(UUID.fromString("aae25387-6a99-4ac1-b78e-44a9af3e0651"), "Brasas", "Evento de brasas", LocalDateTime.of(2000, 5, 5, 18, 30), "argentina", 1, chef1);
        evento1.inscribirParticipante(participante1);

        EventosGastronomicos evento2 = new EventosGastronomicos(UUID.fromString("fef3c5e9-7b71-43d4-9d6e-98b2c7f6f013"), "Fiesta Gourmet", "Evento de cocina gourmet con platos exquisitos.", LocalDateTime.of(2024, 10, 15, 20, 0), "Buenos Aires, Argentina", 50, chef2);
        evento2.inscribirParticipante(participante2);
        evento2.inscribirParticipante(participante3);

        EventosGastronomicos evento3 = new EventosGastronomicos(UUID.fromString("c53b1e2b-1b76-4b5d-8f14-2b429e6c67f0"), "Noche de Parrilla", "Evento con parrillada al aire libre y música en vivo.", LocalDateTime.of(2024, 12, 1, 19, 0), "Córdoba, Argentina", 100, chef3);
        evento3.inscribirParticipante(participante4);
        evento3.inscribirParticipante(participante5);

        EventosGastronomicos evento4 = new EventosGastronomicos(UUID.fromString("926d8c85-1b56-4c78-a5a6-cd9b2b81267a"), "Taller de Repostería", "Clase intensiva de técnicas de repostería.", LocalDateTime.of(2024, 11, 22, 10, 0), "Rosario, Argentina", 20, chef4);
        evento4.inscribirParticipante(participante4);
        evento4.inscribirParticipante(participante5);

        EventosGastronomicos evento5 = new EventosGastronomicos(UUID.fromString("5d4e06b7-8c3e-42c1-8c6a-70ec065e41c2"), "Festival de Vinos", "Evento para degustar vinos de diferentes bodegas.", LocalDateTime.of(2024, 9, 20, 16, 0), "Mendoza, Argentina", 200, chef5);
        evento5.inscribirParticipante(participante2);
        evento5.inscribirParticipante(participante3);

        organizador.getEventos().add(evento1);
        organizador.getEventos().add(evento2);
        organizador.getEventos().add(evento3);
        organizador.getEventos().add(evento4);
        organizador.getEventos().add(evento5);

        // Crear reseñas
        Resena reseña1 = new Resena();
        reseña1.setId(UUID.fromString("b0cde1dd-5cb4-4ae4-b1b8-29d2baa9963f"));
        reseña1.setEvento(evento1);
        reseña1.setParticipante(participante1);
        reseña1.setCalificación(4);
        reseña1.setComentario("Evento muy divertido en familia");

        Resena reseña2 = new Resena();
        reseña2.setId(UUID.fromString("d8d5e3a5-1c52-4f60-9d8e-61b3e4a3bb72"));
        reseña2.setEvento(evento2);
        reseña2.setParticipante(participante2);
        reseña2.setCalificación(5);
        reseña2.setComentario("Un festín de sabores, excelente organización.");

        Resena reseña3 = new Resena();
        reseña3.setId(UUID.fromString("a54d2b3b-2bde-4b6e-a7d8-98b0f9f7e349"));
        reseña3.setEvento(evento3);
        reseña3.setParticipante(participante4);
        reseña3.setCalificación(5);
        reseña3.setComentario("Increíble parrillada y excelente ambiente.");

        evento1.dejarResena(reseña1.getParticipante(), reseña1.getCalificación(), reseña1.getComentario());
        evento2.dejarResena(reseña2.getParticipante(), reseña2.getCalificación(), reseña2.getComentario());
        evento3.dejarResena(reseña3.getParticipante(), reseña3.getCalificación(), reseña3.getComentario());
    
    }
    
}
