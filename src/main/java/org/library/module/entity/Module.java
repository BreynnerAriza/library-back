package org.library.module.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.library.role.entity.Role;
import org.library.shared.entity.Audit;

import java.util.List;
import java.util.UUID;

//Representa el modulo o opciones que tendra el usuario que se loguee
@Entity
@Table(name = "module", schema = "main")
@AllArgsConstructor @NoArgsConstructor
@Getter
public class Module extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "module_id", updatable = false, nullable = false)
    private UUID moduleId;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;
    //Representa el icono que debera pintar el frontend
    @Column(name = "icon", length = 50, nullable = false)
    private String icon;
    @Column(name = "description", length = 100)
    private String description;
    @Column(name = "route", length = 100, unique = true)
    private String route;

    @ManyToMany(mappedBy = "modules", fetch = FetchType.LAZY)
    private List<Role> roles;

}
