package tech.qmates;

public record Level(int level) {
    public int diff(Level that) {
        return this.level - that.level;
    }
}
