package ogustaflor.com.github.logbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Product extends Eloquent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 2, max = 128)
    @Getter
    @Setter
    private String name;

    @Column
    @NotNull
    @Size(max = 64)
    @JsonIgnore
    @Getter
    private String password;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @Getter
    private List<Event> events;

    public void setPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    public void merge(Product targetProduct) {
        name = targetProduct.getName();
        password = targetProduct.getPassword();
    }

}
