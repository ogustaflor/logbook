package ogustaflor.com.github.logbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ogustaflor.com.github.logbook.annotation.Sortable;
import ogustaflor.com.github.logbook.enumeration.Level;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
public class Event extends Eloquent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column
    @NotNull
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
    @Getter
    @Setter
    @Sortable
    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    @NotNull
    @Getter
    @Setter
    @Sortable
    private String log;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Size(min = 4, max = 16)
    @Getter
    @Setter
    @Sortable
    private Level level;

    @ManyToOne
    @NotNull
    @JsonIgnore
    @Getter
    @Setter
    @Sortable
    private Product product;

}
