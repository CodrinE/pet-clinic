package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
@AllArgsConstructor
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @ModelAttribute("types")
    public Collection<PetType> populatePeTypes() {
        return petTypeService.findAll();
    }

    @RequestMapping("owner")
    public Owner findOwners(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBuilder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(@Valid Owner owner, ModelMap model, @PathVariable Long ownerId) {
        owner = ownerService.findById(ownerId);
        Pet pet = Pet.builder().owner(owner).build();
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(@Valid Owner owner, @Valid Pet pet, BindingResult result, ModelMap model,
                                      @PathVariable Long ownerId) {
        owner = ownerService.findById(ownerId);

        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.getPets().add(pet);
        pet.setOwner(owner);

        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            model.put("pet", pet);
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model, Owner owner) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, @Valid Owner owner, BindingResult result,
                                    ModelMap model, @PathVariable Long ownerId) {
        owner = ownerService.findById(ownerId);

        pet.setOwner(owner);

        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            model.addAttribute("pet", pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}

