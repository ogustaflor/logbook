package ogustaflor.com.github.logbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
    private String description;

    @Column
    @NotNull
    @Getter
    @Setter
    private Date date;

    @Column
    @NotNull
    @Getter
    @Setter
    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    @NotNull
    @Getter
    @Setter
    private String log;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Size(min = 4, max = 16)
    @Getter
    @Setter
    private Level level;
    private enum Level { INFO, WARNING, ERROR }

    @ManyToOne
    @NotNull
    @JsonIgnore
    @Getter
    @Setter
    private Product product;

}
