package ar.edu.utn.frba.dds.serviciotres.controller;

import ar.edu.utn.frba.dds.serviciotres.domain.Entidad;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class EntidadesController {
  @PostMapping(path = {"/calcular", "/calcular/"})
  public Map<String, String> calcularEntidad(@RequestBody ArrayList<Entidad> entidades){
    entidades.sort( Comparator.comparingInt(Entidad::calcularImpacto));
    Collections.reverse(entidades);


    Map<String, String> jsonRespuesta = new LinkedHashMap<>();

    for(Entidad entidad : entidades) {
      //aca pongo el nombre de la entidad junto al tiempo promedio de resolucion de sus incidentes
      jsonRespuesta.put(entidad.getNombre(), String.valueOf(entidad.calcularImpacto()));
    }

    return jsonRespuesta;
  }
}
