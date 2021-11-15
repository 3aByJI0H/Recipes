package recipes.presentation.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
public class UserInfo {

    @Pattern(regexp = ".*?@.*?\\..*")
    private String email;

    @NotBlank @Size(min = 8)
    private String password;
}
