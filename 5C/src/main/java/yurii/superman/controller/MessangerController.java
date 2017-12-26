package yurii.superman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yurii.superman.DTO.DTOBuilder;
import yurii.superman.DTO.impl.MessangerDTO;
import yurii.superman.domain.Messanger;
import yurii.superman.service.MessangerService;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class MessangerController {
    @Autowired
    MessangerService messangerService;

    @GetMapping(value = "/api/messanger/person/{id_person}")
    public ResponseEntity<List<MessangerDTO>> getMessangersByPersonId(@PathVariable Long id_person) throws Exception {
        Set<Messanger> messangerList = messangerService.getMessangersByPersonId(id_person);
        Link link = linkTo(methodOn(MessangerController.class).getMessangersByPersonId(id_person)).withSelfRel();
        List<MessangerDTO> messangerDTOList = DTOBuilder.buildDtoListForCollection(messangerList, MessangerDTO.class, link);
        return new ResponseEntity<>(messangerDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/api/messanger/{id_messanger}")
    public ResponseEntity<MessangerDTO> getMessanger(@PathVariable Long id_messanger) throws Exception {
        Messanger messanger = messangerService.getMessanger(id_messanger);
        Link link = linkTo(methodOn(MessangerController.class).getMessanger(id_messanger)).withSelfRel();
        MessangerDTO messangerDTO = DTOBuilder.buildDtoForEntity(messanger, MessangerDTO.class, link);
        return new ResponseEntity<>(messangerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/messanger")
    public ResponseEntity<List<MessangerDTO>> getAllMessangers() {
        List<Messanger> messangerList = messangerService.getAllMessangers();
        Link link = linkTo(methodOn(MessangerController.class).getAllMessangers()).withSelfRel();
        List<MessangerDTO> messangerDTOList = DTOBuilder.buildDtoListForCollection(messangerList, MessangerDTO.class, link);
        return new ResponseEntity<>(messangerDTOList, HttpStatus.OK);
    }

    @PostMapping(value = "/api/messanger")
    public  ResponseEntity<MessangerDTO> addMessanger(@RequestBody Messanger newMessanger) throws Exception {
        messangerService.createMessanger(newMessanger);
        Link link = linkTo(methodOn(MessangerController.class).getMessanger(newMessanger.getId())).withSelfRel();
        MessangerDTO messangerDTO = DTOBuilder.buildDtoForEntity(newMessanger,MessangerDTO.class, link);
        return new ResponseEntity<>(messangerDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/messanger/{id_messanger}")
    public  ResponseEntity<MessangerDTO> updateMessanger(@RequestBody Messanger updateMessanger,
                                                         @PathVariable Long id_messanger) throws Exception {
        messangerService.updateMessanger(updateMessanger, id_messanger);
        Messanger messanger = messangerService.getMessanger(id_messanger);
        Link link = linkTo(methodOn(MessangerController.class).getMessanger(id_messanger)).withSelfRel();
        MessangerDTO messangerDTO = DTOBuilder.buildDtoForEntity(messanger,MessangerDTO.class, link);
        return new ResponseEntity<>(messangerDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/messanger/{id_messanger}")
    public  ResponseEntity deleteMessanger(@PathVariable Long id_messanger) throws Exception {
        messangerService.deleteMessanger(id_messanger);
        return new ResponseEntity(HttpStatus.OK);
    }
}
