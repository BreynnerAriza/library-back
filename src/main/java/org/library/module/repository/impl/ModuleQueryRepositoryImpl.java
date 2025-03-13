package org.library.module.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.library.module.dto.ModuleResponseDto;
import org.library.module.entity.Module;
import org.library.module.entity.Module_;
import org.library.module.repository.IModuleQueryRepository;
import org.library.role.entity.Role;
import org.library.role.entity.Role_;
import org.library.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ModuleQueryRepositoryImpl implements IModuleQueryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ModuleResponseDto> listModuleByUser(User user) {
        List<ModuleResponseDto> modules =  new ArrayList<>();

        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ModuleResponseDto> cq = cb.createQuery(ModuleResponseDto.class);
            Root<Role>  root = cq.from(Role.class);
            Join<Role, Module> joinRoleModule = root.join(Role_.modules);

            //Obtener el rol ya que a el estan ligados los usuarios
            Role role = user.getRole();

            //Indicar que se va a traer
            cq.select(cb.construct(
                    ModuleResponseDto.class,
                    joinRoleModule.get(Module_.moduleId),
                    joinRoleModule.get(Module_.name),
                    joinRoleModule.get(Module_.icon)
            ));

            //Definir filtros
            Predicate predicate = cb.and(
                    cb.equal(root.get(Role_.roleId), role.getRoleId())
            );

            cq.where(predicate);

            TypedQuery<ModuleResponseDto> query = em.createQuery(cq);

            modules = query.getResultList();
        }catch (Exception e){
            log.error("Error al listar los modulos de un usuario {}", e.getMessage());
        }

        em.close();
        return modules;
    }

}
