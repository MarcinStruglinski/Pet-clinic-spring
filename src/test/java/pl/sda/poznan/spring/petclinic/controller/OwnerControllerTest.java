package pl.sda.poznan.spring.petclinic.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sda.poznan.spring.petclinic.aop.ApplicationErrorHandler;
import pl.sda.poznan.spring.petclinic.exception.OwnerNotFoundException;
import pl.sda.poznan.spring.petclinic.model.Address;
import pl.sda.poznan.spring.petclinic.model.Owner;
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
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController)
        .setControllerAdvice(new ApplicationErrorHandler())
        .build();

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
            get("/api/v1/owner/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.firstname").value("Jan"))
        .andExpect(jsonPath("$.lastname").value("Kowalski"))
        .andExpect(jsonPath("$.address.country").value("Poland"))
        .andExpect(status().isOk());
  }

  @Test
  public void should_return_error_when_owner_not_found() throws Exception {
    given(ownerService.findOwnerById(Mockito.anyLong())).willThrow(OwnerNotFoundException.class);
    mockMvc
        .perform(get("/api/v1/owner/21"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void should_return_bad_request_when_id_is_not_a_number() throws Exception {
    mockMvc.perform(get("/api/v1/owner/this-is-not-a-number"))
        .andExpect(status().isBadRequest());
  }

}