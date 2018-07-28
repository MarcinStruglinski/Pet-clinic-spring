package pl.sda.poznan.spring.petclinic.dto;

import lombok.Data;
import pl.sda.poznan.spring.petclinic.model.Owner;
import pl.sda.poznan.spring.petclinic.model.PetType;

import java.util.Date;

@Data
public class PetDto {
    private Long id;
    private String name;
    private Date birthDate;
    private PetType petType;
    private Owner owner;
}
