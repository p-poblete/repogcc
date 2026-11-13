
package com.pokedex.pokedex.controller;

import com.pokedex.pokedex.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final List<Pokemon> pokemones = new ArrayList<>();
    private Long siguienteId = 1L;

    @GetMapping
    public List<Pokemon> listarPokemon() {
        return pokemones;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon registrarPokemon(@RequestBody Pokemon pokemon) {

        if (pokemon.getNombre() == null || pokemon.getNombre().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }

        if (pokemon.getTipo() == null || pokemon.getTipo().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El tipo es obligatorio");
        }

        if (pokemon.getNivel() <= 0 || pokemon.getVida() <= 0
                || pokemon.getAtaque() < 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Los valores numericos son invalidos");
        }

        pokemon.setId(siguienteId++);
        pokemones.add(pokemon);

        return pokemon;
    }
}
