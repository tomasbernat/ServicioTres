package ar.edu.utn.frba.dds.serviciotres.domain;

import ar.edu.utn.frba.dds.serviciotres.configuracion.ConfiguracionPropertiesSingleton;
import lombok.Getter;
import java.util.List;

@Getter
public class Entidad {
  private String nombre;
  private int cantidadMiembros;
  private List<Integer> tiemposResolucionIncidentes;
  private int cantidadIncidentesNoResueltos;

  public int calcularImpacto(){
    int puntaje = 0;

    puntaje = this.getTiemposResolucionIncidentes().stream().reduce(0, Integer::sum);

    puntaje += this.getCantidadIncidentesNoResueltos() * ConfiguracionPropertiesSingleton.getInstance().getCnf();

    puntaje *= this.cantidadMiembros;

    return puntaje;
  }
}
