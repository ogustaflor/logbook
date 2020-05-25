package ogustaflor.com.github.logbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public DTO toDTO() {
        return new DTO(id, name, password);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DTO {

        private Long id;
        private String name;
        private String password;

        public Product toEntity() {
            return new Product(id, name, password, Collections.emptyList());
        }

    }

}
