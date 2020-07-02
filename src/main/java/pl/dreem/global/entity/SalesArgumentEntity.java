package pl.dreem.global.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "sales_argument")
@Entity(name = "SalesArgument")
public class SalesArgumentEntity {

    @Id
    private String id;

    @Column
    private String argument;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "part_id", nullable = false)
    private PartEntity part;

    public SalesArgumentEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public PartEntity getPart() {
        return part;
    }

    public void setPart(PartEntity part) {
        this.part = part;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesArgumentEntity that = (SalesArgumentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(argument, that.argument) &&
                Objects.equals(part, that.part);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, argument, part);
    }

    @Override
    public String toString() {
        return "SalesArgumentEntity{" +
                "id='" + id + '\'' +
                ", argument='" + argument + '\'' +
                ", part=" + part +
                '}';
    }
}
