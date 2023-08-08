package onlineshop.example.beeshop.dto;

import lombok.*;

import javax.validation.constraints.Pattern;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCriteriaDTO {
    private String filterUsername;
    @Pattern(regexp = "^Customer%|^ShopOwner$|^Provider$")
    private String filterRole;
    private String filterEmail;
    private String filterPhone;
    private String filterAddress;
    @Pattern(regexp = "^asc%|^desc$|^")
    private String orderBy;
    @Pattern(regexp = "^[0-9]+$")
    private String page;
}
