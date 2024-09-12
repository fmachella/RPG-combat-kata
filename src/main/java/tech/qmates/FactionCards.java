package tech.qmates;

import java.util.HashSet;
import java.util.stream.Stream;

public class FactionCards {
    private final HashSet<Faction> factionsMemory;

    public FactionCards(HashSet<Faction> factionsMemory) {
        this.factionsMemory = factionsMemory;
    }

    public void add(Faction faction) {
        factionsMemory.add(faction);
    }

    public void remove(Faction faction) {
        factionsMemory.remove(faction);
    }

    public Stream<Faction> stream() {
        return this.factionsMemory.stream();
    }
}
