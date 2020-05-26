package ogustaflor.com.github.logbook.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ogustaflor.com.github.logbook.entity.Event;
import ogustaflor.com.github.logbook.entity.Product;
import ogustaflor.com.github.logbook.enumeration.Level;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
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
