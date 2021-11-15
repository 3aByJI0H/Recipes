package recipes.presentation.mappers;

import org.springframework.stereotype.Component;
import recipes.businesslayer.entities.Recipe;
import recipes.presentation.dtos.RecipeDto;

@Component
public class RecipeMapper {

    public Recipe toRecipe(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        return updateRecipeFromDto(recipe, recipeDto);
    }

    public RecipeDto toRecipeDto(Recipe recipe) {
        RecipeDto recipeDto = new RecipeDto();

        recipeDto.setName(recipe.getName());
        recipeDto.setCategory(recipe.getCategory());
        recipeDto.setDate(recipe.getLastChangeDate());
        recipeDto.setDescription(recipe.getDescription());
        recipeDto.setIngredients(recipe.getIngredients());
        recipeDto.setDirections(recipe.getDirections());

        return recipeDto;
    }

    public Recipe updateRecipeFromDto(Recipe recipe, RecipeDto recipeDto) {
        recipe.setName(recipeDto.getName());
        recipe.setCategory(recipeDto.getCategory());
        recipe.setLastChangeDate(recipeDto.getDate());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setIngredients(recipeDto.getIngredients());
        recipe.setDirections(recipeDto.getDirections());
        return recipe;
    }
}
