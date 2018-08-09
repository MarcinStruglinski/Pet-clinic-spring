package pl.sda.poznan.spring.petclinic.service;

import java.util.Collection;
import pl.sda.poznan.spring.petclinic.model.Owner;
import pl.sda.poznan.spring.petclinic.model.Visit;

public interface VisitService {

  Owner findVisitById(long id);

  Collection<Visit> findAllVisit();

  void save(Visit visit);

  void update(Visit visit);
}
