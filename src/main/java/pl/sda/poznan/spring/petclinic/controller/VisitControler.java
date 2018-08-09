package pl.sda.poznan.spring.petclinic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.poznan.spring.petclinic.model.Visit;
import pl.sda.poznan.spring.petclinic.service.VisitService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(exposedHeaders = "errors, content-type")
public class VisitControler {
    @Autowired
    private final VisitService visitService;

    public VisitControler(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping(
            path = "/addvisit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
            public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
            this.visitService.save(visit);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
