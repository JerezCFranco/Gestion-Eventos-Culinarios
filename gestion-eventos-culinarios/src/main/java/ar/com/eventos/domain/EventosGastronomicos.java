package ar.com.eventos.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Del  sistema se conoce que existen Eventos Gastronómicos,
//  de los cuales estos tendrán un identificador, un nombre, 
//  descripción, fecha y hora, ubicación,
//   capacidad (número máximo de participantes) y un chef a cargo.


public class EventosGastronomicos {
    private UUID id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaYHora;
    private String ubicacion;
    private int capacidad;
    private Chef chefCargo;
    private List<Participante> participantes = new ArrayList<>();
    private List<Resena> resenas = new ArrayList<>();

    public EventosGastronomicos(){}

    public EventosGastronomicos(UUID id, String nombre, String descripcion, LocalDateTime fechaYHora, String ubicacion, int capacidad, Chef chefCargo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaYHora = fechaYHora;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.chefCargo = chefCargo;
        this.participantes = new ArrayList<>();
        this.resenas = new ArrayList<>();
    }


    public UUID getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public Chef getChefCargo() {
        return chefCargo;
    }
    public List<Participante> getParticipantes() {
        return participantes;
    }
    public List<Resena> getResenas() {
        return resenas;
    }


    public void setId(UUID id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public void setChefCargo(Chef chefCargo) {
        this.chefCargo = chefCargo;
    }
    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public boolean inscribirParticipante(Participante participante){
        if(participantes.size() < capacidad){
            participantes.add(participante);
            participante.getHistorialEventos().add(this);
            System.out.println("Participante agregado exitosamente!");
            return true;
        }else{
            System.out.println("Ya no hay cupos de inscripcion para este evento.");
            return false;
        }
    }

    public Participante buscarParticipantePorId(UUID id){
        for(Participante participante : participantes){
            if(participante.getId().equals(id)){
                return participante;
            }
        }
        return null;
    }

    public void dejarResena(Participante participante, int calificación, String comentario){
        if(participantes.contains(participante)){
            Resena nuevaResena = new Resena();
            nuevaResena.setId(UUID.randomUUID());
            nuevaResena.setEvento(this);
            nuevaResena.setParticipante(participante);
            nuevaResena.setCalificación(calificación);
            nuevaResena.setComentario(comentario);
            resenas.add(nuevaResena);
            System.out.println("Reseña agregada con éxito.");
        }else{
            System.out.println("El participante no esta inscripto al evento.");
        }
    }

    public void mostrarResenas(){
        if(resenas.isEmpty()){
            System.out.println("No hay reseñas del evento.");
        }else{
            for(Resena resena : resenas){
                System.out.println("Reseña de " + resena.getParticipante().getNombreYApellido());
                System.out.println("Calificación: " + resena.getCalificación() + " estrellas");
                System.out.println("Comentario: " + resena.getComentario());
            }
        }
    }

    
    public void datosEvento(){
        System.out.println("Id: " + getId() + "\nNombre: " + getNombre() + "\nDescripcion: " + getDescripcion());
        System.out.println("Fecha y Hora: " + getFechaYHora() + "\nUbicacion: " + getUbicacion() + "\nCapacidad: " +getCapacidad());
        System.out.println("Chef a Cargo: " + getChefCargo().getNombre() + "\nLista de Participantes: ");
        mostrarParticipantes();
        System.out.println("Reseñas: ");
        mostrarResenas();
    }

    public void mostrarParticipantes(){
        if(participantes.isEmpty()){
            System.out.println("No hay participantes registrados.");
        }else{
            for(Participante participante : participantes){
                System.out.println("ID: " + participante.getId());
                System.out.println("Nombre y Apellido: " + participante.getNombreYApellido());;
                System.out.println();
            }
        }
    }
}
