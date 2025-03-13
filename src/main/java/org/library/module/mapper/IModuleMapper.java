package org.library.module.mapper;

import org.library.module.dto.ModuleResponseDto;
import org.library.module.entity.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IModuleMapper {

    @Mapping(target = "name", source = "name")
    ModuleResponseDto toModuleResponseDto(Module module);
    List<ModuleResponseDto> toModuleResponseDto(List<Module> modules);

}
