package ar.com.eventos;

import java.util.Scanner;
import ar.com.eventos.domain.Organizador;
import ar.com.eventos.service.archivos.ArchivosEventosService;
import ar.com.eventos.service.archivos.impl.ArchivosEventosServiceImpl;
import ar.com.eventos.service.chef.ChefService;
import ar.com.eventos.service.chef.impl.ChefServiceImpl;
import ar.com.eventos.service.eventos.EventosGastronomicosService;
import ar.com.eventos.service.eventos.impl.EventosGastronomicosServiceImpl;
import ar.com.eventos.service.menu.MenuService;
import ar.com.eventos.service.menu.Impl.MenuServiceImpl;
import ar.com.eventos.service.organizador.OrganizadorService;
import ar.com.eventos.service.organizador.impl.OrganizadorServiceImpl;
import ar.com.eventos.service.participante.ParticipanteService;
import ar.com.eventos.service.participante.impl.ParticipanteServiceImpl;
import ar.com.eventos.service.resena.ResenaService;
import ar.com.eventos.service.resena.impl.ResenaServiceImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Organizador organizador = new Organizador();

        OrganizadorService organizadorService = new OrganizadorServiceImpl(organizador);
        organizadorService.inicializarDatos();
        
        ChefService chefService = new ChefServiceImpl(organizadorService);

        ParticipanteService participanteService = new ParticipanteServiceImpl(organizador);

        EventosGastronomicosService eventosGastronomicosService = new EventosGastronomicosServiceImpl(chefService, organizadorService, participanteService);

        ResenaService resenaService = new ResenaServiceImpl(eventosGastronomicosService, participanteService);
        
        ArchivosEventosService archivosEventosService = new ArchivosEventosServiceImpl(organizadorService);

        MenuService menuService = new MenuServiceImpl(eventosGastronomicosService, participanteService, chefService, resenaService, archivosEventosService);

        Scanner scan = new Scanner(System.in);
        
        menuService.mostrarMenu(scan);

        scan.close();
    }
}

