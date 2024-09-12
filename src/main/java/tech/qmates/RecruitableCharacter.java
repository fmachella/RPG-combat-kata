package tech.qmates;

public interface RecruitableCharacter {
    void join(Faction faction);

    void quit(Faction faction);

    boolean isHeAllied(RecruitableCharacter character);
}
