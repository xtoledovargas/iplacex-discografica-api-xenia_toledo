package org.iplacex.proyectos.discografia.artistas;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class artistas {
@Id @Field(name="_id") public String _id;
@Field("nombre") public String nombre;
  @Field("estilos") public List<String> estilos;
  @Field("anioFundacion") public int anioFundacion;
  @Field("estaActivo") public boolean estaActivo;
  public artistas() {}
}
