package tech.qmates;

import java.util.HashSet;

public class Faction {
    private final HashSet<Character> factionPool;

    public Faction(HashSet<Character> factionPool) {
        this.factionPool = factionPool;
    }

    public void subscribe(Character character) {
        factionPool.add(character);
    }

    public void strikeOff(Character lonewolf) {
        this.factionPool.remove(lonewolf);
    }
}
