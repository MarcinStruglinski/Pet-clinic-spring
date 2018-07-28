package pl.sda.poznan.spring.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.exception.OwnerNotFoundException;
import pl.sda.poznan.spring.petclinic.model.Owner;
import pl.sda.poznan.spring.petclinic.repository.OwnerRepository;

@Service
public class BaseOwnerService implements OwnerService {

  private final OwnerRepository ownerRepository;

  public BaseOwnerService(OwnerRepository ownerRepository) {
    this.ownerRepository = ownerRepository;
  }

  @Override
  public Owner findOwnerById(long id) {
    // owner repository zwraca typ Optional, reprezentujący, że Owner może być dostepny lub nie
    // jesli nie istnial owner o podanym ID to Optional będzie empty
    // wywołanie metody get() na nim spowoduje błąd
    // jeśli Owner o takim id był w bazie to zostanie on poprawnie zwróciony
    Optional<Owner> optionalOwner = ownerRepository.findById(id);
    // zwróć owner'a lub jeśli go nie ma to rzuc wyjątek OwnerNotFoundException
    Owner owner = optionalOwner.orElseThrow(OwnerNotFoundException::new);
    return owner;

    // ewentualnie krótszy zapis
    // return ownerRepository.findById(id).orElseThrow(OwnerNotFoundException::new);
  }

  @Override
  public Collection<Owner> findAllOwners() {
    Iterable<Owner> all = this.ownerRepository.findAll();
    List<Owner> ownerList = new ArrayList<>();
    all.forEach(ownerList::add);
    return ownerList;
  }

  @Override
  public void saveOwner(Owner owner) {
    this.ownerRepository.save(owner);
  }

  @Override
  public Collection<Owner> findOwnerByLastname(String lastname) {
    return null;
  }

  @Override
  public void update(Owner owner) {
    if (owner.getId() == null || !this.ownerRepository.existsById(owner.getId())) {
      throw new OwnerNotFoundException();
    }
    this.ownerRepository.save(owner);
  }
}
