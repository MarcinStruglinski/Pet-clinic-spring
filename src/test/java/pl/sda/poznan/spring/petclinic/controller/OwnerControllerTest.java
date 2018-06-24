package pl.sda.poznan.spring.petclinic.controller;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sda.poznan.spring.petclinic.model.Address;
import pl.sda.poznan.spring.petclinic.model.Owner;
import pl.sda.poznan.spring.petclinic.service.BaseOwnerService;
import pl.sda.poznan.spring.petclinic.service.OwnerService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class OwnerControllerTest {

  @Autowired
  private OwnerController ownerController; // ta klase chcemy przetestowac

  @MockBean
  private OwnerService ownerService;

  private MockMvc mockMvc;

  private List<Owner> owners;

  @Before
  public void initOwners() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    MockitoAnnotations.initMocks(this);

    owners = new ArrayList<>();
    Owner owner = new Owner();
    owner.setId(1L);
    owner.setFirstname("Jan");
    owner.setLastname("Kowalski");
    Address address = new Address();
    address.setCity("Poznań");
    address.setCountry("Poland");
    address.setStreet("Półwiejska");
    address.setPostalcode("61-229");
    owner.setAddress(address);
    owners.add(owner);
  }

  @Test
  public void should_return_owner_by_id() throws Exception {
    // mockowanie wywolania metody findOwnerById(1) -> gdy będzie wywołana ta metoda to zwróć pierwszy element z listy owners
    given(ownerService.findOwnerById(1)).willReturn(owners.get(0));

    // wywolanie żądania
    mockMvc
        .perform(
            get("/api/v1/owners/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

}