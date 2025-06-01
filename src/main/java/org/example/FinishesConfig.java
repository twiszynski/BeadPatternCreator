package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FinishesConfig {

    public static final Map<String, Map<String, Integer>> EFFECT_CONFIGS = new HashMap<>();

    // wartosci przezroczystosci efektow: 100 -> pelne krycie ; 0 -> pelna przezroczystosc

    static {
        // Basic - when particular effect not present in this config map
        Map<String, Integer> basic = new LinkedHashMap<>();
        basic.put("BaseColor", 100);          // pełna widoczność koloru bazowego
        basic.put("AB.png", 80);
        basic.put("Metallic.png", 60); // nakładka metaliczna z przezroczystością 60%
        EFFECT_CONFIGS.put("Basic", basic);

        // MetallicAB
        Map<String, Integer> metallicAB = new LinkedHashMap<>();
        metallicAB.put("BaseColor", 100);          // pełna widoczność koloru bazowego
        metallicAB.put("Metallic_strong.png", 60); // nakładka metaliczna z przezroczystością 60%
        metallicAB.put("AB.png", 50);              // efekt AB z przezroczystością 80%
        EFFECT_CONFIGS.put("MetallicAB", metallicAB);

        // Dodaj kolejne efekty zgodnie z potrzebami...
    }


}
