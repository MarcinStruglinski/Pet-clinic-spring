package pl.sda.poznan.spring.petclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.poznan.spring.petclinic.dto.PetDto;
import pl.sda.poznan.spring.petclinic.model.Pet;
import pl.sda.poznan.spring.petclinic.service.PetService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(exposedHeaders = "errors, content-type")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(path = "/pet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Pet> createPet(@RequestBody PetDto pet){
        this.petService.savePet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
