package ogustaflor.com.github.logbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ogustaflor.com.github.logbook.annotation.Sortable;
import ogustaflor.com.github.logbook.entity.dto.EventDTO;
import ogustaflor.com.github.logbook.enumeration.Level;

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

    public EventDTO toDTO() {
        return new EventDTO(id, description, date, quantity, level, log, product.getId());
    }

}
