package tech.qmates;

public record Level(int level) {

    public boolean isOverpowerFor(Level myself) {
        return this.level - myself.level >= 5;
    }

    public boolean isWeakerFor(Level myself) {
        return myself.level - this.level >= 5;
    }
}
