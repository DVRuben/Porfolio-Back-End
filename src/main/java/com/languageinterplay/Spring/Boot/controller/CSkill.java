package com.languageinterplay.Spring.Boot.controller;

import com.languageinterplay.Spring.Boot.Dto.dtoSkill;
import com.languageinterplay.Spring.Boot.Security.Controller.Mensaje;
import com.languageinterplay.Spring.Boot.controller.model.Skill;
import com.languageinterplay.Spring.Boot.service.SSkill;
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
@RequestMapping("/skill")
@CrossOrigin(origins = "http://localhost:4200")
public class CSkill {
    @Autowired
    SSkill sSkill;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skill>> list(){
        List<Skill> list = sSkill.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skill> getById(@PathVariable("id")int id){
        if(!sSkill.existsById(id)){
            return new ResponseEntity(new Mensaje("Este ID no está registrado."), HttpStatus.BAD_REQUEST);
        }
        
        Skill skill = sSkill.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sSkill.existsById(id)){
            return new ResponseEntity(new Mensaje("No se pudo encontrar el id en el registro."), HttpStatus.NOT_FOUND);
        }
        sSkill.delete(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkill dtoskill){
        if(StringUtils.isBlank(dtoskill.getNombreSkill())){
            return new ResponseEntity(new Mensaje("Ingrese un nombre"), HttpStatus.BAD_REQUEST);
        }
        if(sSkill.existsByNombreSkill(dtoskill.getNombreSkill())){
            return new ResponseEntity(new Mensaje("Nombre ya existente"), HttpStatus.BAD_REQUEST);
        }
        
        Skill skill = new Skill(
                dtoskill.getNombreSkill(), dtoskill.getImgSkill()
        );
        sSkill.save(skill);
        return new ResponseEntity(new Mensaje("Nuevo registro creado correctamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkill dtoskill) {
        if(!sSkill.existsById(id)){
            return new ResponseEntity(new Mensaje("No se ha encontrado este ID."), HttpStatus.NOT_FOUND);
        }
        if(sSkill.existsByNombreSkill(dtoskill.getNombreSkill()) && sSkill.getByNombreSkill(dtoskill.getNombreSkill()).get().getId() != id){
            return new ResponseEntity(new Mensaje("El registro ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoskill.getNombreSkill())){
            return new ResponseEntity(new Mensaje("Debe completar este campo."), HttpStatus.BAD_REQUEST);
        }
        
        Skill skill = sSkill.getOne(id).get();
        
        skill.setNombreSkill(dtoskill.getNombreSkill());
        skill.setImgSkill(dtoskill.getImgSkill());
        
        sSkill.save(skill);
        
        return new ResponseEntity(new Mensaje("Registro actualizado"), HttpStatus.OK);
    }
}