package tech.qmates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class FactionsTest {

    RecruitableCharacter lonewolf;
    HashSet<RecruitableCharacter> ramas_faction_pool;
    Faction ramas_knights;

    @BeforeEach
    void setUp() {
        lonewolf = new ComposedCharacter();
        ramas_faction_pool = new HashSet<>();
        ramas_knights = new Faction(ramas_faction_pool);
    }

    @Test
    void ramas_knights_subscribe_lonewolf() {
        Faction ramas_knights = new Faction(ramas_faction_pool);
        ramas_knights.subscribe(lonewolf);

        assertTrue(ramas_faction_pool.contains(lonewolf));
    }

    @Test
    void ramas_knights_strikeOff_lonewolf() {
        ramas_faction_pool.add(lonewolf);

        ramas_knights.strikeOff(lonewolf);

        assertTrue(ramas_faction_pool.isEmpty());
    }
}
