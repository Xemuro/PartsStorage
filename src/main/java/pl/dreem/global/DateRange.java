package pl.dreem.global;

import java.time.LocalDate;
import java.util.Objects;

public final class DateRange {

    private final LocalDate from;
    private final LocalDate to;

    private DateRange(final LocalDate from, final LocalDate to) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public static DateRange from(final LocalDate from, final LocalDate to){
        if(to.isBefore(from)){
            throw new RuntimeException("To should be before From");
        }
        return new DateRange(from, to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(from, dateRange.from) &&
                Objects.equals(to, dateRange.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
