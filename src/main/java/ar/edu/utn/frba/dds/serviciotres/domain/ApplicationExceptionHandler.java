package ar.edu.utn.frba.dds.serviciotres.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;

@ControllerAdvice
public class ApplicationExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<HashMap<String, String>> manejarParametroInvalido(NullPointerException exception){
    HashMap<String, String> jsonResponse = new HashMap<>();

    return ResponseEntity.badRequest().body(jsonResponse);
  }
}
