package com.vet.VetCenter.framework.adapters.in.dtos.mappers;

import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.framework.adapters.in.dtos.request.GuardianRequest;
import com.vet.VetCenter.framework.adapters.in.dtos.response.GuardianResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface GuardianMapper {

    GuardianMapper INSTANCE = Mappers.getMapper(GuardianMapper.class);

    GuardianResponse toModel (Guardian guardian);

    Guardian toObject (GuardianRequest request);
}
