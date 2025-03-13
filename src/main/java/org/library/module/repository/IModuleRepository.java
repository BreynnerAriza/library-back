package org.library.module.repository;

import org.library.module.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IModuleRepository extends JpaRepository<Module, UUID> {

    @Query("SELECT module FROM Module module JOIN module.roles role WHERE role.roleId = :roleId")
    List<Module> findModulesByRoleId(@Param("roleId") UUID roleId);

}
