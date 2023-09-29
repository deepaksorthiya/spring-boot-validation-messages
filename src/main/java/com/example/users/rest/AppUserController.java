package com.example.users.rest;

import com.example.users.model.AppUser;
import com.example.users.repo.AppUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {
    private final AppUserRepository appUserRepository;
    private final EntityManager entityManager;

    public AppUserController(AppUserRepository appUserRepository, EntityManager entityManager) {
        this.appUserRepository = appUserRepository;
        this.entityManager = entityManager;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> save(@RequestBody @Valid AppUser appUser, @RequestHeader HttpHeaders httpHeaders) {
        return new ResponseEntity<>(appUserRepository.save(appUser), HttpStatus.CREATED);
    }

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> getUser(@PathVariable long userId, @RequestHeader HttpHeaders httpHeaders) {
        return new ResponseEntity<>(appUserRepository.findById(userId).get(), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AppUser>> findAll(@NotNull final Pageable pageable,
                                                 @RequestHeader HttpHeaders httpHeaders) {
        System.out.println(entityManager);
        Map<String, Object> properties = entityManager.getProperties();
        System.out.println(properties);
        return new ResponseEntity<>(appUserRepository.findAll(pageable), HttpStatus.OK);
    }
}
