package com.vet.VetCenter.framework.adapters.in.dtos.mappers;

import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.framework.adapters.in.dtos.request.AnimalRequest;
import com.vet.VetCenter.framework.adapters.in.dtos.response.AnimalResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AnimalMapper {

    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    AnimalResponse toModel(Animal animal);

    Animal toObject(AnimalRequest request);
}
