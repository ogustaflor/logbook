package ogustaflor.com.github.logbook.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ogustaflor.com.github.logbook.entity.Product;

import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Product toEntity() {
        return new Product(id, name, password, Collections.emptyList());
    }

}
