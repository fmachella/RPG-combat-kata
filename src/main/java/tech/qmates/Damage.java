package tech.qmates;

public record Damage(int damages) {
    public static final Damage ZERO = new Damage(0);

    public Damage halven(){
        return new Damage(damages/2);
    }

    public Damage doubleIt() {
        return new Damage(damages*2);
    }
}
