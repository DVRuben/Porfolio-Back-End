package com.languageinterplay.Spring.Boot.service;

import com.languageinterplay.Spring.Boot.controller.model.Persona;
import com.languageinterplay.Spring.Boot.controller.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService{
    @Autowired
    PersonaRepository persoRepo; 
    
    public List<Persona> list(){
        return persoRepo.findAll();
    }
    
    public Optional<Persona> getOne(int id){
        return persoRepo.findById(id);
    }
    
    public Optional<Persona> getByNombre(String nombre){
        return persoRepo.findByNombre(nombre);
    }
    
    public void save(Persona persona){
        persoRepo.save(persona);
    }
    
    public void delete(int id){
        persoRepo.deleteById(id);
    }
    
    public boolean existsById(int id){
        return persoRepo.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return persoRepo.existsByNombre(nombre);
    }
}
