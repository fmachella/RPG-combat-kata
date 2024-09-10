package tech.qmates;

public class Health {
    private final int hitPoints;

    public Health(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Health damage(int hitPoints) {
        return new Health(this.hitPoints - hitPoints);
    }

    public Health heal(int hitPoints) {
        return new Health(this.hitPoints + hitPoints);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Health)) return false;

        Health health = (Health) o;
        return hitPoints == health.hitPoints;
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
