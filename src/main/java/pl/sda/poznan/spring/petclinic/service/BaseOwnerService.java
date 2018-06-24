package pl.sda.poznan.spring.petclinic.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.model.Owner;

@Service
public class BaseOwnerService implements OwnerService {

  @Override
  public Owner findOwnerById(long id) {
    return null;
  }

  @Override
  public Collection<Owner> findAllOwners() {
    return null;
  }

  @Override
  public void saveOwner(Owner owner) {

  }

  @Override
  public Collection<Owner> findOwnerByLastname(String lastname) {
    return null;
  }
}
