package pl.sda.poznan.spring.petclinic.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import pl.sda.poznan.spring.petclinic.model.Owner;
import pl.sda.poznan.spring.petclinic.model.Visit;
import pl.sda.poznan.spring.petclinic.repository.VisitRepository;
@Service
public class BaseVisitService implements VisitService {

  private final VisitRepository visitRepository;

  public BaseVisitService(VisitRepository visitRepository) {
    this.visitRepository = visitRepository;
  }

  @Override
  public Owner findVisitById(long id) {
    return null;
  }

  @Override
  public Collection<Visit> findAllVisit() {
    return null;
  }

  @Override
  public void save(Visit visit) {
    this.visitRepository.save(visit);
  }

  @Override
  public void update(Visit visit) {}
}
