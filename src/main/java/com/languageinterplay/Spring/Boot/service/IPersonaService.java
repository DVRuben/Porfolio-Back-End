package com.languageinterplay.Spring.Boot.service;

import com.languageinterplay.Spring.Boot.controller.model.Persona;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    public List<Persona> verPersona();
    public void crearPersona(Persona persona);
    public void borrarPersona(Long id);
    public Persona findPersona(Long id);
    public boolean existsById(Long id);
    public boolean existsByNombre(String nombre);
    public Optional<Persona> getByNombre(String nombre);
}
