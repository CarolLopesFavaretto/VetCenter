package com.vet.VetCenter.framework.adapters.in.dtos.mappers;

import com.vet.VetCenter.domain.entity.Consultation;
import com.vet.VetCenter.framework.adapters.in.dtos.request.ConsultationRequest;
import com.vet.VetCenter.framework.adapters.in.dtos.response.ConsultationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ConsultationMapper {

    ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);

    ConsultationResponse toModel(Consultation consultation);

    Consultation toObject(ConsultationRequest request);
}
