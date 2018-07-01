package pl.sda.poznan.spring.petclinic.service;

import java.util.Collection;
import pl.sda.poznan.spring.petclinic.model.Owner;

public interface OwnerService {

  Owner findOwnerById(long id);

  Collection<Owner> findAllOwners();

  void saveOwner(Owner owner);

  Collection<Owner> findOwnerByLastname(String lastname);

  void update(Owner owner);
}
