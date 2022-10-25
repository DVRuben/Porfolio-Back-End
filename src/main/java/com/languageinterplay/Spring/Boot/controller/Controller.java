package com.languageinterplay.Spring.Boot.controller;

import com.languageinterplay.Spring.Boot.Dto.dtoPersona;
import com.languageinterplay.Spring.Boot.Security.Controller.Mensaje;
import com.languageinterplay.Spring.Boot.controller.model.Persona;
import com.languageinterplay.Spring.Boot.service.PersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    @Autowired
    PersonaService persoServ;
        
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = persoServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
        if(!persoServ.existsById(id)){
            return new ResponseEntity(new Mensaje("Este ID no está registrado."), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = persoServ.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!persoServ.existsById(id)){
            return new ResponseEntity(new Mensaje("No se pudo encontrar el id en el registro."), HttpStatus.NOT_FOUND);
        }
        persoServ.delete(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("Ingrese un nombre"), HttpStatus.BAD_REQUEST);
        }
        if(persoServ.existsByNombre(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("Nombre ya existente"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = new Persona(
                dtopersona.getNombre(), dtopersona.getPosition(), dtopersona.getImg(), dtopersona.getPresentation()
        );
        persoServ.save(persona);
        return new ResponseEntity(new Mensaje("Registro creado correctamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona) {
        if(!persoServ.existsById(id)){
            return new ResponseEntity(new Mensaje("No se ha encontrado este ID."), HttpStatus.NOT_FOUND);
        }
        if(persoServ.existsByNombre(dtopersona.getNombre()) && persoServ.getByNombre(dtopersona.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("El registro ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("Debe completar este campo."), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = persoServ.getOne(id).get();
        
        persona.setNombre(dtopersona.getNombre());
        persona.setPosition(dtopersona.getPosition());
        persona.setImg(dtopersona.getImg());
        persona.setPresentation(dtopersona.getPresentation());
        
        persoServ.save(persona);
        
        return new ResponseEntity(new Mensaje("El registro se actualizó correctamente."), HttpStatus.OK);
    }
}