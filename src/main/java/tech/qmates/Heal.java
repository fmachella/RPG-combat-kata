package tech.qmates;

public record Heal(int heals) {
    public static final Heal ANY = new Heal(Math.toIntExact(Math.round(Math.random() * 100)));
}
