package ar.com.eventos.service.chef;

import java.util.UUID;

import ar.com.eventos.domain.Chef;

public interface ChefService {
    
    Chef crearChef();

    void mostrarChefs();

    Chef buscarChef(UUID id);
}
