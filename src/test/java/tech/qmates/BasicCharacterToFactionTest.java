package tech.qmates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Qui potrebbe nascondersi il concetto di registro che tiene la lista di fazioni e membri e le gestisce lei
public class BasicCharacterToFactionTest {

    HashSet<RecruitableCharacter> kaiMemory;
    Faction kaiKnights;
    RecruitableCharacter lonewolf;
    HashSet<RecruitableCharacter> ramasMemory;
    Faction ramasKnights;
    private HashSet<Faction> lonewolfFactionCards;

    @BeforeEach
    void setUp() {
        kaiMemory = new HashSet<>();
        ramasMemory = new HashSet<>();
        kaiKnights = new Faction(kaiMemory);
        ramasKnights = new Faction(ramasMemory);
        lonewolfFactionCards = new HashSet<>();
        lonewolf = new Character(new Membership(lonewolfFactionCards));
    }

    @Test
    void lonewolf_joins_kai_knights() {
        lonewolf.join(kaiKnights);

        assertTrue(kaiMemory.contains(lonewolf));
        assertTrue(lonewolfFactionCards.contains(kaiKnights));
    }

    @Test
    void lonewolf_joins_ramas_and_kai_knights() {
        lonewolf.join(kaiKnights);
        lonewolf.join(ramasKnights);

        assertTrue(kaiMemory.contains(lonewolf));
        assertTrue(ramasMemory.contains(lonewolf));
        assertTrue(lonewolfFactionCards.contains(kaiKnights));
        assertTrue(lonewolfFactionCards.contains(ramasKnights));
    }

    @Test
    void lonewolf_quit_kai_knights() {
        kaiMemory.add(lonewolf);

        lonewolf.quit(kaiKnights);

        assertTrue(kaiMemory.isEmpty());
        assertTrue(lonewolfFactionCards.isEmpty());
    }

    @Test
    void thunder_hawk_is_a_lonewolf_allied() {
        HashSet<Faction> hawkFactionCards = new HashSet<>();
        RecruitableCharacter ramasMasterThunderHawk = new Character(new Membership(hawkFactionCards));
        ramasMemory.add(ramasMasterThunderHawk);
        ramasMemory.add(lonewolf);
        lonewolfFactionCards.add(ramasKnights);
        hawkFactionCards.add(ramasKnights);


        assertTrue(lonewolf.isHeAllied(ramasMasterThunderHawk));
    }

    @Test
    void black_knight_is_a_lonewolf_foo() {
        RecruitableCharacter blackNight = new Character();
        ramasMemory.add(lonewolf);
        lonewolfFactionCards.add(ramasKnights);

        assertFalse(lonewolf.isHeAllied(blackNight));
    }
}
