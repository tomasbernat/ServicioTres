package ar.edu.utn.frba.dds.serviciotres.configuracion;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.util.Properties;

@Component
public class ConfiguracionPropertiesSingleton {
  private static float cnf = 0f;
  private static String pathConfiguracion = "src/main/resources/configuracion.properties";
  private static ConfiguracionPropertiesSingleton instancia = null;

  private ConfiguracionPropertiesSingleton(){

  }

  public static ConfiguracionPropertiesSingleton getInstance(){
    if(instancia == null){
      instancia = new ConfiguracionPropertiesSingleton();
    }
    return instancia;
  }

  public float getCnf(){
    return cnf;
  }

  /**
   *
   * @return retorna el valor "cnf" del archivo configuracion.properties
   *
   */

  @Scheduled(fixedDelay = 60000)
  private void leerCnf(){
    Properties propiedades = new Properties();
    FileInputStream archivoConfiguracion;

    try{
      archivoConfiguracion = new FileInputStream(pathConfiguracion);
      propiedades.load(archivoConfiguracion);

      cnf = Float.parseFloat( propiedades.getProperty("cnf") );

      System.out.println("Valor de CNF actualizado a " + cnf);
    } catch (Exception e){
      System.out.println("El valor de cnf en el archivo es invalido. Se mantiene su valor anterior (" + cnf + ")");
      System.out.println(e);
    }
  }
}