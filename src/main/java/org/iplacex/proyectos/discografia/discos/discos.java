package org.iplacex.proyectos.discografia.discos;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class discos {
@Id @Field(name="_id") public String _id;
  @Field("idArtista") public String idArtista;
  @Field("nombre") public String nombre;
  @Field("anioLanzamiento") public int anioLanzamiento;
  @Field("canciones") public List<String> canciones;
  public discos() {}
}
