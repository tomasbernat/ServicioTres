package ar.edu.utn.frba.dds.serviciotres.controller;

import ar.edu.utn.frba.dds.serviciotres.domain.ApplicationExceptionHandler;
import ar.edu.utn.frba.dds.serviciotres.domain.Entidad;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class EntidadesController {
  /**
   *
   * @param entidades recibe una lista de entidades con los datos para ser ordenadas
   * @return retorna la lista ordenada de entidades
   */
  @PostMapping(path = {"/ordenar"})
  @ApiOperation(value = "Ordenar entidades", notes = "Recibe una lista de entidades y la retorna ordenada")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Se retorna la lista ordenada."),
      @ApiResponse(code = 400, message = "Hubo un error.")
  })
  public ResponseEntity< Map<String, String> > ordenarEntidades(@ApiParam(value = "Lista de entidades") @RequestBody(required = false) ArrayList<Entidad> entidades){
    Map<String, String> jsonRespuesta = new LinkedHashMap<>();


    entidades.sort(Comparator.comparingInt(Entidad::calcularImpacto));
    Collections.reverse(entidades);


    for(Entidad entidad : entidades) {
      //aca pongo el nombre de la entidad junto al tiempo promedio de resolucion de sus incidentes
      jsonRespuesta.put(entidad.getNombre(), String.valueOf(entidad.calcularImpacto()));
    }

    return ResponseEntity.ok(jsonRespuesta);
  }
}
