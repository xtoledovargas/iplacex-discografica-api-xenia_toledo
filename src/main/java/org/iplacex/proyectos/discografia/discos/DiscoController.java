package org.iplacex.proyectos.discografia.discos;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @CrossOrigin @RequestMapping("/api")
public class DiscoController {
  private final IDiscoRepository discoRepo;
  private final IArtistaRepository artistaRepo;

  public DiscoController(IDiscoRepository d, IArtistaRepository a){ this.discoRepo = d; this.artistaRepo = a; }

  @PostMapping(value="/disco", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> HandlePostDiscoRequest(@RequestBody discos d){
    if(d.idArtista == null || !artistaRepo.existsById(d.idArtista))
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Artista no existe (idArtista inválido)"));
    return ResponseEntity.status(HttpStatus.CREATED).body(discoRepo.save(d));
  }

  @GetMapping(value="/discos", produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<discos>> HandleGetDiscosRequest(){
    return ResponseEntity.ok(discoRepo.findAll());
  }

  @GetMapping(value="/disco/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> HandleGetDiscoRequest(@PathVariable String id){
    return discoRepo.findById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Disco no encontrado")));
  }

  @GetMapping(value="/artista/{id}/discos", produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<discos>> HandleGetDiscosByArtistaRequest(@PathVariable String id){
    return ResponseEntity.ok(discoRepo.findDiscosByIdArtista(id));
  }
}
