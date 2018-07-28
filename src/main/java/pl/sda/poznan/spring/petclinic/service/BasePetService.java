package pl.sda.poznan.spring.petclinic.service;

import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.dto.PetDto;
import pl.sda.poznan.spring.petclinic.model.Pet;
import pl.sda.poznan.spring.petclinic.repository.PetRepository;

@Service
public class BasePetService implements PetService {

    private final PetRepository petRepository;

  public BasePetService(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  public void savePet(PetDto petDto) {
    Pet pet = new Pet();
    pet.setId(petDto.getId());
    pet.setBirthDate(petDto.getBirthDate());
    pet.setName(petDto.getName());
    pet.setOwner(petDto.getOwner());
    pet.setPetType(petDto.getPetType());
    this.petRepository.save(pet);
  }
}
