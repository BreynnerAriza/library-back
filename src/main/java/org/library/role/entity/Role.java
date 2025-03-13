package org.library.role.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.library.module.entity.Module;
import org.library.shared.entity.Audit;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "role", schema = "main")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Role extends Audit {

    //Representa el id del rol
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id", updatable = false)
    private UUID roleId;

    //Representa el nombre del rol
    @Column(name = "name", unique = true, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "module_role", schema = "main",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "module_id"})
    )
    private List<Module> modules;

    /**
     * Permite crear un rol
     * @param name Representa el nombre del rol
     */
    public Role(String name) {
        this.name = name;
    }

}
