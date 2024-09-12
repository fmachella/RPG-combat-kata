package tech.qmates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterToFactionTest {

    HashSet<Character> kaiMemory;
    Faction kaiKnights;
    Character lonewolf;
    HashSet<Character> ramasMemory;
    Faction ramasKnights;

    @BeforeEach
    void setUp() {
        kaiMemory = new HashSet<>();
        ramasMemory = new HashSet<>();
        kaiKnights = new Faction(kaiMemory);
        ramasKnights = new Faction(ramasMemory);
        lonewolf = new Character();
    }

    @Test
    void lonewolf_joins_kai_knights() {
        lonewolf.join(kaiKnights);

        assertTrue(kaiMemory.contains(lonewolf));
    }

    @Test
    void lonewolf_joins_ramas_and_kai_knights() {
        lonewolf.join(kaiKnights);
        lonewolf.join(ramasKnights);

        assertTrue(kaiMemory.contains(lonewolf));
        assertTrue(ramasMemory.contains(lonewolf));
    }

    @Test
    @Disabled
    void thunder_hawk_is_a_lonewolf_allied() {
        Character ramasMasterThunderHawk = new Character();
        ramasMasterThunderHawk.join(ramasKnights);
        lonewolf.join(ramasKnights);

        assertTrue(lonewolf.isHeAllied(ramasMasterThunderHawk));
    }
}
