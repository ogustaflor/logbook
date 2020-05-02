package ogustaflor.com.github.logbook.entity;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "events")
public class Event extends Eloquent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String log;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id")
    private Service service;

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String value) {
        this.log = log;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
