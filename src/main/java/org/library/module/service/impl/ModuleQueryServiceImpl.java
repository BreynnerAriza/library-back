package org.library.module.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.auth.service.IAuthQueryService;
import org.library.module.dto.ModuleResponseDto;
import org.library.module.entity.Module;
import org.library.module.mapper.IModuleMapper;
import org.library.module.repository.IModuleRepository;
import org.library.module.service.IModuleQueryService;
import org.library.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModuleQueryServiceImpl implements IModuleQueryService {

    private final IModuleRepository moduleRepository;
    private final IModuleMapper moduleMapper;
    private final IAuthQueryService authQueryService;

    //Permite listar todos los modulos asociados a un usuario
    @Transactional(readOnly = true)
    @Override
    public List<ModuleResponseDto> listModuleByUser() {
        User user = authQueryService.getUserAuthenticate(); //Obtener el usuario que se encuentra autenticado
        List<Module> modules = moduleRepository.findModulesByRoleId(user.getRole().getRoleId());
        return moduleMapper.toModuleResponseDto(modules);
    }

}
