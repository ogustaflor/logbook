package ogustaflor.com.github.logbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ogustaflor.com.github.logbook.entity.dto.ProductDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Eloquent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(unique = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    @Getter
    @Setter
    private String name;

    @Column(length = 64)
    @NotNull
    @NotBlank
    @Getter
    private String password;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @Getter
    private List<Event> events;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public ProductDTO toDTO() {
        return new ProductDTO(id, name, password);
    }

}
