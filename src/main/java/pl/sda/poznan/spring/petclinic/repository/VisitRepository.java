package pl.sda.poznan.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.poznan.spring.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {}
