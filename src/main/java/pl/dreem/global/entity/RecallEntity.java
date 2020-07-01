package pl.dreem.global.entity;

import pl.dreem.command.domain.recall.dto.NewRecallDto;
import pl.dreem.global.identity.PartId;
import pl.dreem.global.identity.RecallId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Table(name = "recall")
@Entity
public class RecallEntity {

    @Id
    private String id;

    @Column
    private String name;

    @Column(name = "part_id")
    private String partId;

    @Column(name = "recall_start")
    private LocalDate recallStart;

    @Column(name = "recall_stop")
    private LocalDate recallFinish;

    public RecallEntity() {
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

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public LocalDate getRecallStart() {
        return recallStart;
    }

    public void setRecallStart(LocalDate recallStart) {
        this.recallStart = recallStart;
    }

    public LocalDate getRecallFinish() {
        return recallFinish;
    }

    public void setRecallFinish(LocalDate recallFinish) {
        this.recallFinish = recallFinish;
    }

    public RecallId getRecallId() {
        return RecallId.from(UUID.fromString(id));
    }

    public PartId getPartIdAsId() {
        return PartId.from(UUID.fromString(partId));
    }

    public static RecallEntity from(final NewRecallDto newRecall){
        final RecallEntity entity = new RecallEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setName(newRecall.getName());
        entity.setPartId(newRecall.getPartId().getIdAsLiteral());
        entity.setRecallStart(newRecall.getRecallStart());
        entity.setRecallFinish(newRecall.getRecallFinish());
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecallEntity that = (RecallEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(partId, that.partId) &&
                Objects.equals(recallStart, that.recallStart) &&
                Objects.equals(recallFinish, that.recallFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, partId, recallStart, recallFinish);
    }

    @Override
    public String toString() {
        return "RecallEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", partId='" + partId + '\'' +
                ", recallStart=" + recallStart +
                ", recallFinish=" + recallFinish +
                '}';
    }
}
