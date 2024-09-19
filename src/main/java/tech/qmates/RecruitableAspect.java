package tech.qmates;

class RecruitableAspect {

    private final Character self;
    private final Membership factions;

    public RecruitableAspect(Membership factions, Character recruitableCharacter) {
        this.factions = factions;
        this.self = recruitableCharacter;
    }

    public boolean isHeAllied(Character friendOrFoe) {
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
