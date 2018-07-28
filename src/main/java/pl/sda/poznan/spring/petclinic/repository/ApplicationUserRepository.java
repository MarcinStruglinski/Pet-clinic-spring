package pl.sda.poznan.spring.petclinic.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.poznan.spring.petclinic.model.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
  Optional<ApplicationUser> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<ApplicationUser> findByactivationHash(String token);
}
