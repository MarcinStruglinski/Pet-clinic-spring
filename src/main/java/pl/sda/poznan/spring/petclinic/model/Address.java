package pl.sda.poznan.spring.petclinic.model;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

  private String street;
  private String postalcode;
  private String city;
  private String country;
}
