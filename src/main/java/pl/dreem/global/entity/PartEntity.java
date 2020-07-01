package pl.dreem.global.entity;

import pl.dreem.global.identity.PartId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table(name ="part")
@Entity(name = "part")
public class PartEntity {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "parts")
    private List<ModelEntity> modelsDetails;

    @Column
    private BigDecimal price;

    @Column
    private boolean available;

    @Column(name = "ship_days")
    private int numberOfDaysToShip;

    @OneToMany(mappedBy = "part", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SalesArgumentEntity> salesArguments;

    public PartEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ModelEntity> getModelsDetails() {
        return modelsDetails;
    }

    public void setModelsDetails(List<ModelEntity> modelsDetails) {
        this.modelsDetails = modelsDetails;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getNumberOfDaysToShip() {
        return numberOfDaysToShip;
    }

    public void setNumberOfDaysToShip(int numberOfDaysToShip) {
        this.numberOfDaysToShip = numberOfDaysToShip;
    }

    public List<SalesArgumentEntity> getSalesArguments() {
        return salesArguments;
    }

    public void setSalesArguments(List<SalesArgumentEntity> salesarguments) {
        this.salesArguments = salesarguments;
    }

    public PartId getPartId(){
        return PartId.from(UUID.fromString(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartEntity that = (PartEntity) o;
        return available == that.available &&
                numberOfDaysToShip == that.numberOfDaysToShip &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(modelsDetails, that.modelsDetails) &&
                Objects.equals(price, that.price) &&
                Objects.equals(salesArguments, that.salesArguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, modelsDetails, price, available, numberOfDaysToShip, salesArguments);
    }

    @Override
    public String toString() {
        return "PartEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", modelsDetails=" + modelsDetails +
                ", price=" + price +
                ", available=" + available +
                ", numberOfDaysToShip=" + numberOfDaysToShip +
                ", salesArguments=" + salesArguments +
                '}';
    }
}