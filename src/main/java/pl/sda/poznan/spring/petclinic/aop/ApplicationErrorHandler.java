package pl.sda.poznan.spring.petclinic.aop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sda.poznan.spring.petclinic.exception.ApplicationUserIsActiveException;
import pl.sda.poznan.spring.petclinic.exception.ApplicationUserNotFoundException;
import pl.sda.poznan.spring.petclinic.exception.EmailAlreadyRegisteredException;
import pl.sda.poznan.spring.petclinic.exception.OwnerNotFoundException;

@ControllerAdvice
public class ApplicationErrorHandler {

  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity handleNumberFormatException() {
    return ResponseEntity.badRequest().build();
  }

  @ExceptionHandler(OwnerNotFoundException.class)
  public ResponseEntity handleOwnerNotFoundException() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(EmailAlreadyRegisteredException.class)
  public ResponseEntity handleEmailAlreadyRegisteredException() {
    return ResponseEntity.badRequest().build();
  }

  @ExceptionHandler(ApplicationUserNotFoundException.class)
  public ResponseEntity handleApplicationUserNotFoundException() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(ApplicationUserIsActiveException.class)
  public ResponseEntity handleApplicationUserIsActiveException() {
    return ResponseEntity.badRequest().build();
  }
}
