package org.library.module.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.library.role.entity.Role;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Module.class)
public abstract class Module_ {

    public static volatile SingularAttribute<Module, UUID> moduleId;
    public static volatile SingularAttribute<Module, String> name;
    public static volatile SingularAttribute<Module, String> icon;
    public static volatile SingularAttribute<Module, String> description;
    public static volatile ListAttribute<Module, Role> roles;

}
