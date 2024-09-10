package tech.qmates;

public class Life {
    private final int hitPoints;

    public Life(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Life damage(int hitPoints) {
        return new Life(this.hitPoints - hitPoints);
    }

    public Life heal(int hitPoints) {
        return new Life(this.hitPoints + hitPoints);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Life)) return false;

        Life life = (Life) o;
        return hitPoints == life.hitPoints;
    }

    @Override
    public int hashCode() {
        return hitPoints;
    }

    @Override
    public String toString() {
        return "Life{" +
                "hitPoints=" + hitPoints +
                '}';
    }
}
