package onlineshop.example.beeshop.model;

import lombok.*;

import javax.validation.constraints.Pattern;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCriteriaModel {
    private String filterUsername;
    @Pattern(regexp = "^Customer%|^Shop_Owner$|^Provider$")
    private String filterRole;
    @Pattern(regexp = "^Online%|^Offline$")
    private String filterStatus;
    @Pattern(regexp = "^asc%|^desc$|^")
    private String orderBy;
    @Pattern(regexp = "^[0-9]+$")
    private String page;
}
