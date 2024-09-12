package tech.qmates;

public interface FactionableCharacter {
    void join(Faction faction);

    void quit(Faction faction);

    boolean isHeAllied(FactionableCharacter character);
}
