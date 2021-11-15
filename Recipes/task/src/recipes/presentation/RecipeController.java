package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.businesslayer.entities.Recipe;
import recipes.businesslayer.entities.User;
import recipes.persistence.RecipeRepository;
import recipes.persistence.UserRepository;
import recipes.presentation.dtos.RecipeDto;
import recipes.presentation.filters.RecipeFilter;
import recipes.presentation.mappers.RecipeMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final RecipeMapper recipeMapper;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, UserRepository userRepository,
                            RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.recipeMapper = recipeMapper;
    }

    @PostMapping("/new")
    public Map<String, Integer> create(@Valid @RequestBody RecipeDto recipeDto, Authentication auth) {
        Recipe recipe = recipeMapper.toRecipe(recipeDto);
        User author = userRepository.findById(auth.getName()).orElseThrow();
        recipe.setAuthor(author);
        int id = recipeRepository.save(recipe).getId();
        return Map.of("id", id);
    }

    @GetMapping("/{id}")
    public RecipeDto get(@PathVariable int id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return recipeMapper.toRecipeDto(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, Authentication auth) {
        String fromEmail = auth.getName();
        HttpStatus status =  recipeRepository.findById(id)
                .map(recipe -> {
                    validateRequestFromRecipeAuthor(recipe, fromEmail);
                    recipeRepository.delete(recipe);
                    return HttpStatus.NO_CONTENT;
                }).orElse(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody RecipeDto recipeDto,
                                    Authentication auth) {
        String fromEmail = auth.getName();
        HttpStatus status =  recipeRepository.findById(id)
                .map(recipe -> {
                    validateRequestFromRecipeAuthor(recipe, fromEmail);
                    recipeRepository.save(recipeMapper.updateRecipeFromDto(recipe, recipeDto));
                    return HttpStatus.NO_CONTENT;
                }).orElse(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(status);
    }

    @GetMapping("/search")
    public List<RecipeDto> find(@Valid RecipeFilter filter) {
        return recipeRepository.findAll(filter.getSpecification(), filter.getOrder())
                .stream()
                .map(recipeMapper::toRecipeDto)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateRequestFromRecipeAuthor(Recipe recipe, String fromEmail) {
        if (!recipe.getAuthor().getEmail().equals(fromEmail)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
