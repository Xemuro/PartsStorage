package pl.dreem.global.entity;

import pl.dreem.global.DateRange;
import pl.dreem.global.identity.ModelId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table(name = "model")
@Entity(name = "model")
public class ModelEntity {

    @Id
    private String id;

    @Column
    private String make;

    @Column
    private String model;

    @Column(name = "production_from")
    private LocalDate productionStart;

    @Column(name = "production_to")
    private LocalDate productionHold;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "model_part",
            joinColumns = { @JoinColumn(name = "model_id") },
            inverseJoinColumns = { @JoinColumn(name = "part_id") }
    )
    private List<PartEntity> parts;

    public ModelEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getProductionStart() {
        return productionStart;
    }

    public void setProductionStart(LocalDate productionStart) {
        this.productionStart = productionStart;
    }


    public LocalDate getProductionHold() {
        return productionHold;
    }

    public void setProductionHold(LocalDate productionHold) {
        this.productionHold = productionHold;
    }

    public List<PartEntity> getParts() {
        return parts;
    }

    public void setParts(List<PartEntity> parts) {
        this.parts = parts;
    }

    public ModelId getModelId(){
        return ModelId.from(UUID.fromString(id));
    }

    public DateRange getProductionRange(){
        return DateRange.from(productionStart, productionHold);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelEntity that = (ModelEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                Objects.equals(productionStart, that.productionStart) &&
                Objects.equals(productionHold, that.productionHold) &&
                Objects.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, productionStart, productionHold, parts);
    }

    @Override
    public String toString() {
        return "ModelEntity{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", productionStart=" + productionStart +
                ", productionHold=" + productionHold +
                ", parts=" + parts +
                '}';
    }
}
