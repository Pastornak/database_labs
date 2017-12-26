package yurii.superman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yurii.superman.DTO.DTOBuilder;
import yurii.superman.DTO.impl.GenderDTO;
import yurii.superman.domain.Gender;
import yurii.superman.service.GenderService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GenderController {
    @Autowired
    GenderService genderService;

    @GetMapping(value = "/api/gender")
    public ResponseEntity<List<GenderDTO>> getAllGenders() {
        List<Gender> genderList = genderService.getAllGenders();
        Link link = linkTo(methodOn(GenderController.class).getAllGenders()).withSelfRel();
        List<GenderDTO> genders = DTOBuilder.buildDtoListForCollection(genderList, GenderDTO.class, link);
        return new ResponseEntity<>(genders, HttpStatus.OK);
    }

    @GetMapping(value = "/api/gender/{id_gender}")
    public ResponseEntity<GenderDTO> getGender(@PathVariable Long id_gender) throws Exception {
        Gender gender = genderService.getGender(id_gender);
        Link link = linkTo(methodOn(GenderController.class).getGender(id_gender)).withSelfRel();
        GenderDTO genderDTO = DTOBuilder.buildDtoForEntity(gender, GenderDTO.class, link);
        return new ResponseEntity<>(genderDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/gender/{id_gender}")
    public  ResponseEntity<GenderDTO> addGender(@RequestBody Gender newGender) throws Exception {
        genderService.createGender(newGender);
        Link link = linkTo(methodOn(GenderService.class).getGender(newGender.getId())).withSelfRel();
        GenderDTO genderDTO = DTOBuilder.buildDtoForEntity(newGender, GenderDTO.class, link);
        return new ResponseEntity<>(genderDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/gender/{id_gender}")
    public  ResponseEntity<GenderDTO> updateCity(@RequestBody Gender updateGender, @PathVariable Long id_gender)
            throws Exception {
        genderService.updateGender(updateGender, id_gender);
        Gender gender = genderService.getGender(id_gender);
        Link link = linkTo(methodOn(GenderController.class).getGender(id_gender)).withSelfRel();
        GenderDTO genderDTO = DTOBuilder.buildDtoForEntity(gender, GenderDTO.class, link);
        return new ResponseEntity<>(genderDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/gender/{id_gender}")
    public  ResponseEntity deleteGender(@PathVariable Long id_gender) throws Exception {
        genderService.deleteGender(id_gender);
        return new ResponseEntity(HttpStatus.OK);
    }
}
