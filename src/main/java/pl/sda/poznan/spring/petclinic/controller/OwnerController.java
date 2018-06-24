package pl.sda.poznan.spring.petclinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.poznan.spring.petclinic.model.Owner;
import pl.sda.poznan.spring.petclinic.service.OwnerService;

@RestController
@RequestMapping("/api/v1")
public class OwnerController {

  private final OwnerService ownerService;

  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @GetMapping(path = "/owner/{id}")
  public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
    return null;
  }

//
//  @GetMapping(path = "/owner/{lastname}")
//  public ResponseEntity<Owner> getOwnerByLastName(@PathVariable String lastname){
//    return null;
//  }
}
