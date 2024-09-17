package tech.qmates;

class ConcreteRecruitableCharacter implements RecruitableCharacter {

    private final RecruitableCharacter self;
    private final Membership factions;

    public ConcreteRecruitableCharacter(Membership factions, RecruitableCharacter recruitableCharacter) {
        this.factions = factions;
        this.self = recruitableCharacter;
    }

    public boolean isHeAllied(RecruitableCharacter friendOrFoe) {
        return factions.stream().anyMatch(faction -> faction.isHeAMember(friendOrFoe));
    }

    public void quit(Faction faction) {
        this.factions.remove(faction);
        faction.strikeOff(self);
    }

    public void join(Faction faction) {
        faction.subscribe(self);
        this.factions.add(faction);
    }
}
