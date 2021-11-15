package recipes.presentation.filters;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface Filter<T> {
    Specification<T> getSpecification();

    Sort getOrder();
}
