package tech.qmates;

public record Damage(int damages) {
    public Damage halven(){
        return new Damage(damages/2);
    }
}
