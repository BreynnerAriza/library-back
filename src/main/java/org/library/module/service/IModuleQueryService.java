package org.library.module.service;

import org.library.module.dto.ModuleResponseDto;
import org.library.user.entity.User;

import java.util.List;

public interface IModuleQueryService {

    List<ModuleResponseDto> listModuleByUser();

}
