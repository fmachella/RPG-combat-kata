package tech.qmates;

import java.util.HashSet;

public class Faction {
    private final HashSet<RecruitableCharacter> factionPool;

    public Faction(HashSet<RecruitableCharacter> factionPool) {
        this.factionPool = factionPool;
    }

    public void subscribe(RecruitableCharacter character) {
        factionPool.add(character);
    }

    public void strikeOff(RecruitableCharacter character) {
        this.factionPool.remove(character);
    }

    public boolean isHeAMember(RecruitableCharacter character) {
        return this.factionPool.contains(character);
    }
}
