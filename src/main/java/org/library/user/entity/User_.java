package org.library.user.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.library.role.entity.Role;
import org.library.shared.entity.Audit;
import org.library.user.userstatus.UserStatus;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends Audit {

    public static volatile SingularAttribute<User, UUID> userId;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, UserStatus> status;
    public static volatile SingularAttribute<User, Role> role;

}
