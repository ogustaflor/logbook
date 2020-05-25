package ogustaflor.com.github.logbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ogustaflor.com.github.logbook.annotation.Sortable;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Event extends Eloquent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @NotNull
    @NotBlank
    @Getter
    @Setter
    @Sortable
    private String description;

    @Column
    @NotNull
    @Getter
    @Setter
    @Sortable
    private Date date;

    @Column
    @NotNull
    @Positive
    @Getter
    @Setter
    @Sortable
    private Integer quantity = 1;

    @Column(length = 16)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Getter
    @Setter
    @Sortable
    private Level level = Level.INFO;
    private enum Level {
        INFO, WARNING, ERROR
    }

    @Column(columnDefinition = "TEXT")
    @NotNull
    @NotBlank
    @Getter
    @Setter
    @Sortable
    private String log;

    @ManyToOne
    @NotNull
    @Getter
    @Setter
    @Sortable
    private Product product;

    public DTO toDTO() {
        return new DTO(id, description, date, quantity, level, log, product.getId());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DTO {
        private Long id;
        private String description;
        private Date date;
        private Integer quantity;
        private Level level;
        private String log;
        private Long productId;

        public Event toEntity() {
            Product product = new Product();
            product.setId(productId);
            return new Event(id, description, date, quantity, level, log, product);
        }

    }

}
