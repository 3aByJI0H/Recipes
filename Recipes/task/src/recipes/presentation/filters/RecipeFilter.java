package recipes.presentation.filters;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import recipes.businesslayer.entities.Recipe;
import recipes.businesslayer.entities.Recipe_;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RecipeFilter implements Filter<Recipe> {

    private Specification<Recipe> searchBy;
    @Min(1) @Max(1) private int settersCounter;

    @Override
    public Specification<Recipe> getSpecification() {
        return searchBy;
    }

    @Override
    public Sort getOrder() {
        return Sort.by(Sort.Direction.DESC, Recipe_.LAST_CHANGE_DATE);
    }

    public void setName(@NotBlank String name) {
        searchBy = (root, query, cb) ->
                cb.like(cb.lower(root.get(Recipe_.name)), "%"+name.toLowerCase()+"%");
        ++settersCounter;
    }

    public void setCategory(@NotBlank String category) {
        searchBy = (root, query, cb) ->
                cb.equal(cb.lower(root.get(Recipe_.category)), category.toLowerCase());
        ++settersCounter;
    }
}