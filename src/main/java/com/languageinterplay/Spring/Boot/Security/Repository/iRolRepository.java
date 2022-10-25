package com.languageinterplay.Spring.Boot.Security.Repository;

import com.languageinterplay.Spring.Boot.Security.Entity.Rol;
import com.languageinterplay.Spring.Boot.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
