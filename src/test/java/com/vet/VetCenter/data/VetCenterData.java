package com.vet.VetCenter.data;

import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.domain.entity.Consultation;
import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.domain.entity.Prescription;

import java.time.LocalDate;

public class VetCenterData {

    public static Guardian getGuardian() {
        Guardian guardian = new Guardian();
        guardian.setId(1L);
        guardian.setName("Caroline");
        guardian.setCpf("400287319521");
        guardian.setTelephone(1140543188L);
        return guardian;
    }

    public static Animal getAnimal() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Marie");
        animal.setAge(11);
        animal.setType("felino");
        animal.setRace("Tomba Lata");
        animal.setGuardianId(getGuardian().getId());
        return animal;
    }

    public static Consultation getConsultation() {
        Consultation consultation = new Consultation();
        consultation.setId(1L);
        consultation.setNameVeterinary("Maria");
        consultation.setCause("coluna");
        consultation.setDate(LocalDate.now());
        consultation.setObservations("raio-x realizado");
        consultation.setValue(80.00);
        consultation.setRegress(true);
        consultation.setAnimalId(getAnimal().getId());
        return consultation;
    }

    public static Prescription getPrescription() {
        Prescription prescription = new Prescription();
        prescription.setId(1L);
        prescription.setMedication("Dipirona");
        prescription.setDate(LocalDate.now());
        prescription.setConsultationId(getConsultation().getId());
        return prescription;
    }


}
