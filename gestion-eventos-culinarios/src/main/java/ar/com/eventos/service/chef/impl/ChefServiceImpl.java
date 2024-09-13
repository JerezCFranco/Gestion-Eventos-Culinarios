package ar.com.eventos.service.chef.impl;

import java.util.Scanner;
import java.util.UUID;

import ar.com.eventos.domain.Chef;
import ar.com.eventos.enumeration.EspecialidadEnum;
import ar.com.eventos.service.chef.ChefService;
import ar.com.eventos.service.organizador.OrganizadorService;

public class ChefServiceImpl implements ChefService{

    private OrganizadorService organizadorService;

    public ChefServiceImpl(OrganizadorService organizadorService){
        this.organizadorService = organizadorService;
    }

    @Override
    public Chef crearChef() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese el nombre del chef: ");
        String nombreChef = scan.nextLine();

        System.out.println("Ingrese la especialidad del nuevo chef");
        System.out.println(
                "(opciones: INTERNACIONAL, GOURMET, SALSAS, ASADOS, PARRILLA, FRITO, VERDURAS, REPOSTERIA, CARNICERIA): ");
        EspecialidadEnum especialidadChef = EspecialidadEnum.valueOf(scan.nextLine().toUpperCase());

        Chef nuevoChef = new Chef(UUID.randomUUID(), nombreChef, especialidadChef);
        organizadorService.getChefs().add(nuevoChef);
        System.out.println(
                "Chef creado con éxito: " + nuevoChef.getNombre() + ", Especialidad: " + nuevoChef.getEspecialidad());
        return nuevoChef;
    }

    @Override
    public void mostrarChefs() {
        if (organizadorService.getChefs().isEmpty()) {
            System.out.println("No hay chefs registrados.");
            return;
        }
        System.out.println("Lista de chefs: ");
        for (Chef chef : organizadorService.getChefs()) {
            chef.verChef();
        }
    }

    @Override
    public Chef buscarChef(UUID id) {
        for (Chef chef : organizadorService.getChefs()) {
            if (chef.getId().equals(id)) {
                return chef;
            }
        }
        System.out.println("No hay ningun chef con ese ID.");
        return null;
    }

    @Override
    public Chef getChefByNombre(String nombre) {
        Chef chefAsignado = null;
        for (Chef chef : organizadorService.getChefs()) {
            if (chef.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("El Chef fue encontrado!.");
                chefAsignado = chef;
            }else{
                System.out.println("El Chef no ha podido ser encontrado.");
            }
        };
        return chefAsignado;
    }
    
}
