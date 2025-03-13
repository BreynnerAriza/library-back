package org.library.module.repository;

import org.library.module.dto.ModuleResponseDto;
import org.library.user.entity.User;

import java.util.List;

public interface IModuleQueryRepository {

    List<ModuleResponseDto> listModuleByUser(User user);

}
