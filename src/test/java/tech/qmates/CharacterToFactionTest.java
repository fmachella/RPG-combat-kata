package tech.qmates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

//Qui potrebbe nascondersi il concetto di registro che tiene la lista di fazioni e membri e le gestisce lei
public class CharacterToFactionTest {

    HashSet<Character> kaiMemory;
    Faction kaiKnights;
    Character lonewolf;
    HashSet<Character> ramasMemory;
    Faction ramasKnights;
    private HashSet<Faction> lonewolfFactionCards;

    @BeforeEach
    void setUp() {
        kaiMemory = new HashSet<>();
        ramasMemory = new HashSet<>();
        kaiKnights = new Faction(kaiMemory);
        ramasKnights = new Faction(ramasMemory);
        lonewolfFactionCards = new HashSet<>();
        lonewolf = new Character(lonewolfFactionCards);
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
        Character ramasMasterThunderHawk = new Character(hawkFactionCards);
        ramasMemory.add(ramasMasterThunderHawk);
        ramasMemory.add(lonewolf);
        lonewolfFactionCards.add(ramasKnights);
        hawkFactionCards.add(ramasKnights);


        assertTrue(lonewolf.isHeAllied(ramasMasterThunderHawk));
    }
}
