package recipes.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class RecipeDto {

    @NotBlank private String name;
    @NotBlank private String category;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime date = LocalDateTime.now();

    @NotBlank private String description;
    @NotEmpty private List<String> ingredients;
    @NotEmpty private List<String> directions;
}
