package com.vet.VetCenter.framework.adapters.in.dtos.mappers;

import com.vet.VetCenter.domain.entity.Prescription;
import com.vet.VetCenter.framework.adapters.in.dtos.request.PrescriptionRequest;
import com.vet.VetCenter.framework.adapters.in.dtos.response.PrescriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PrescriptionMapper {

    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

    PrescriptionResponse toModel(Prescription prescription);

    Prescription toObject(PrescriptionRequest request);
}
