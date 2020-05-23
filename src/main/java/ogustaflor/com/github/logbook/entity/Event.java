package ogustaflor.com.github.logbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(columnDefinition = "TEXT")
    @NotNull
    @NotBlank
    @Getter
    @Setter
    @Sortable
    private String log;

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

    @ManyToOne
    @NotNull
    @Getter
    @Setter
    @Sortable
    private Product product;

}
