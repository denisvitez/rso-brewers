package si.fri.rso.sn.brewers.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "brewers")
@NamedQueries(value =
{
    @NamedQuery(name = "Brewers.getAll", query = "SELECT b FROM brewers b")
})
public class Brewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private double name;

    @Column(name = "country")
    private String country;

    @Column(name = "date_inserted")
    private Instant dateInserted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Instant dateInserted) {
        this.dateInserted = dateInserted;
    }

    public double getName() {
        return name;
    }

    public void setName(double name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}