package tech.qmates;

public class Health {
    private final int hitPoints;

    public Health(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Health damage(Damage hitPoints) {
        return new Health(this.hitPoints - hitPoints.damages());
    }

    public Health heal(Heal hitPoints) {
        return new Health(this.hitPoints + hitPoints.heals());
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
        return "Health{" +
                "hitPoints=" + hitPoints +
                '}';
    }
}
