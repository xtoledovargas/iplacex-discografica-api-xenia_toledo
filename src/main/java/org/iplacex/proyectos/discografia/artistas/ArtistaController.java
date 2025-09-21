package org.iplacex.proyectos.discografia.artistas;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @CrossOrigin @RequestMapping("/api")
public class ArtistaController {
  private final IArtistaRepository repo;
  public ArtistaController(IArtistaRepository repo){ this.repo = repo; }

  @PostMapping(value="/artista", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<artistas> HandleInsertArtistaRequest(@RequestBody artistas a){
    return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(a));
  }

  @GetMapping(value="/artistas", produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<artistas>> HandleGetAristasRequest(){
    return ResponseEntity.ok(repo.findAll());
  }

  @GetMapping(value="/artista/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> HandleGetArtistaRequest(@PathVariable String id){
    return repo.findById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Artista no encontrado")));
  }

  @PutMapping(value="/artista/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> HandleUpdateArtistaRequest(@PathVariable String id, @RequestBody artistas a){
    if(!repo.existsById(id))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Artista no existe"));
    a._id = id;
    return ResponseEntity.ok(repo.save(a));
  }

  @DeleteMapping(value="/artista/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> HandleDeleteArtistaRequest(@PathVariable String id){
    if(!repo.existsById(id))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Artista no existe"));
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
