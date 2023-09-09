package ar.edu.utn.frba.dds.serviciotres.domain;

import ar.edu.utn.frba.dds.serviciotres.configuracion.ConfiguracionPropertiesSingleton;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import java.util.List;

@Getter
@ApiModel(description = "Información de la entidad.")
public class Entidad {
  @ApiModelProperty(notes = "Nombre de la entidad")
  private String nombre;
  @ApiModelProperty(notes = "Cantidad de miembros de la entidad")
  private int cantidadMiembros;

  @ApiModelProperty(notes = "Tiempos de resolucion de los incidentes resueltos")
  private List<Integer> tiemposResolucionIncidentes;

  @ApiModelProperty(notes = "La cantidad de incidentes no resueltos")
  private int cantidadIncidentesNoResueltos;

  @Operation(summary = "Calcula el impacto que genera la entidad", description = "Retorna un int basado en los incidentes de la entidad y la cantidad de miembros.")
  public int calcularImpacto(){
    int puntaje = 0;

    puntaje = this.getTiemposResolucionIncidentes().stream().reduce(0, Integer::sum);

    puntaje += this.getCantidadIncidentesNoResueltos() * ConfiguracionPropertiesSingleton.getInstance().getCnf();

    puntaje *= this.cantidadMiembros;

    return puntaje;
  }
}
