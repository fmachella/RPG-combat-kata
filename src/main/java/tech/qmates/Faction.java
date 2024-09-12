package tech.qmates;

import java.util.HashSet;

public class Faction {
    private final HashSet<FactionableCharacter> factionPool;

    public Faction(HashSet<FactionableCharacter> factionPool) {
        this.factionPool = factionPool;
    }

    public void subscribe(FactionableCharacter character) {
        factionPool.add(character);
    }

    public void strikeOff(FactionableCharacter character) {
        this.factionPool.remove(character);
    }

    public boolean isHeAMember(FactionableCharacter character) {
        return this.factionPool.contains(character);
    }
}
