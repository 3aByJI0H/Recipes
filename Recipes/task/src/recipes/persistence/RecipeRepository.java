package recipes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import recipes.businesslayer.entities.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>,
        JpaSpecificationExecutor<Recipe> {
}
