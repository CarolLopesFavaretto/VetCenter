package com.vet.VetCenter.data;

import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.domain.entity.Guardian;

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


}
