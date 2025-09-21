package org.iplacex.proyectos.discografia.discos;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IDiscoRepository extends MongoRepository<discos, String> {
  @Query("{ 'idArtista': ?0 }")
  List<discos> findDiscosByIdArtista(String idArtista); 
}
