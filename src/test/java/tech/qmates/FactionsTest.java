package tech.qmates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class FactionsTest {

    Character lonewolf;
    HashSet<Character> ramas_faction_pool;
    Faction ramas_knights;

    @BeforeEach
    void setUp() {
        lonewolf = new Character();
        ramas_faction_pool = new HashSet<>();
        ramas_knights = new Faction(ramas_faction_pool);
    }

    @Test
    void lonewolf_joins_ramas_knights() {
        Faction ramas_knights = new Faction(ramas_faction_pool);
        ramas_knights.subscribe(lonewolf);

        assertTrue(ramas_faction_pool.contains(lonewolf));
    }

    @Test
    void lonewolf_leaves_ramas_knights() {
        ramas_faction_pool.add(lonewolf);

        ramas_knights.strikeOff(lonewolf);

        assertTrue(ramas_faction_pool.isEmpty());
    }
}
