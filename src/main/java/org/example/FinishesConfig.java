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
        basic.put("Transparent_strong.png", 65); // nakładka metaliczna z przezroczystością 60%
        basic.put("BaseColor2", 50);          // pełna widoczność koloru bazowego
        EFFECT_CONFIGS.put("Basic", basic);


        // AB
        Map<String, Integer> ab = new LinkedHashMap<>();
        ab.put("BaseColor", 100);
        ab.put("AB.png", 80);
        EFFECT_CONFIGS.put("AB", ab);

        // ABCrystal
        Map<String, Integer> abCrystal = new LinkedHashMap<>();
        abCrystal.put("BaseColor", 98);
        abCrystal.put("Opaque.png", 30);
        abCrystal.put("AB.png", 80);
        EFFECT_CONFIGS.put("ABCrystal", abCrystal);

        // ABCrystalSatin
        Map<String, Integer> abCrystalSatin = new LinkedHashMap<>();
        abCrystalSatin.put("BaseColor", 100);
        abCrystalSatin.put("AB.png", 70);
        abCrystalSatin.put("Satin.png", 40);
        EFFECT_CONFIGS.put("ABCrystalSatin", abCrystalSatin);

        // ABLined
        Map<String, Integer> abLined = new LinkedHashMap<>();
        abLined.put("BaseColor", 90);
        abLined.put("Lined.png", 70);
        abLined.put("BaseColor2", 20);
        abLined.put("AB.png", 60);
        EFFECT_CONFIGS.put("ABLined", abLined);

        // ABLinedCrystal
        Map<String, Integer> abLinedCrystal = new LinkedHashMap<>();
        abLinedCrystal.put("BaseColor", 90);
        abLinedCrystal.put("Lined.png", 95);
        abLinedCrystal.put("BaseColor2", 20);
        abLinedCrystal.put("Opaque.png", 30);
        abLinedCrystal.put("AB.png", 50);
        EFFECT_CONFIGS.put("ABLinedCrystal", abLinedCrystal);

        // ABLinedCrystalSparkling
        Map<String, Integer> abLinedCrystalSparkling = new LinkedHashMap<>();
        abLinedCrystalSparkling.put("BaseColor", 90);
        abLinedCrystalSparkling.put("Lined_strong.png", 100);
        abLinedCrystalSparkling.put("BaseColor2", 25);
        abLinedCrystalSparkling.put("AB.png", 70);
        EFFECT_CONFIGS.put("ABLinedCrystalSparkling", abLinedCrystalSparkling);

        // ABLinedSparkling
        Map<String, Integer> abLinedSparkling = new LinkedHashMap<>();
        abLinedSparkling.put("BaseColor", 90);
        abLinedSparkling.put("Lined_strong.png", 100);
        abLinedSparkling.put("BaseColor2", 25);
        abLinedSparkling.put("AB.png", 70);
        EFFECT_CONFIGS.put("ABLinedSparkling", abLinedSparkling);

        // ABMatte
        Map<String, Integer> abMatte = new LinkedHashMap<>();
        abMatte.put("BaseColor", 100);
        abMatte.put("AB.png", 55);
        abMatte.put("Matte.png", 65);
        EFFECT_CONFIGS.put("ABMatte", abMatte);

        // ABOpaque
        Map<String, Integer> abOpaque = new LinkedHashMap<>();
        abOpaque.put("BaseColor", 100);
        abOpaque.put("Opaque.png", 50);
        abOpaque.put("AB.png", 60);
        EFFECT_CONFIGS.put("ABOpaque", abOpaque);

        // ABOpaqueMatte
        Map<String, Integer> abOpaqueMatte = new LinkedHashMap<>();
        abOpaqueMatte.put("BaseColor", 100);
        abOpaqueMatte.put("Opaque.png", 40);
        abOpaqueMatte.put("BaseColor2", 20);
        abOpaqueMatte.put("AB.png", 50);
        abOpaqueMatte.put("Matte.png", 50);
        EFFECT_CONFIGS.put("ABOpaqueMatte", abOpaqueMatte);

        // ABOpaqueMatteGlazed
        Map<String, Integer> abOpaqueMatteGlazed = new LinkedHashMap<>();
        abOpaqueMatteGlazed.put("BaseColor", 100);
        abOpaqueMatteGlazed.put("Opaque.png", 50);
        abOpaqueMatteGlazed.put("BaseColor2", 25);
        abOpaqueMatteGlazed.put("AB.png", 60);
        abOpaqueMatteGlazed.put("Matte.png", 50);
        EFFECT_CONFIGS.put("ABOpaqueMatteGlazed", abOpaqueMatteGlazed);

        // ABSilverLined
        Map<String, Integer> abSilverLined = new LinkedHashMap<>();
        abSilverLined.put("BaseColor", 90);
        abSilverLined.put("Lined_strong.png", 100);
        abSilverLined.put("BaseColor2", 20);
        abSilverLined.put("AB.png", 80);
        EFFECT_CONFIGS.put("ABSilverLined", abSilverLined);

        // ABSilverLinedGlazed
        Map<String, Integer> abSilverLinedGlazed = new LinkedHashMap<>();
        abSilverLinedGlazed.put("BaseColor", 90);
        abSilverLinedGlazed.put("Lined_strong.png", 100);
        abSilverLinedGlazed.put("BaseColor2", 20);
        abSilverLinedGlazed.put("Opaque.png", 40);
        abSilverLinedGlazed.put("AB.png", 60);
        EFFECT_CONFIGS.put("ABSilverLinedGlazed", abSilverLinedGlazed);

        // ABTransparent
        Map<String, Integer> abTransparent = new LinkedHashMap<>();
        abTransparent.put("BaseColor", 90);
        abTransparent.put("Transparent_strong.png", 75);
        abTransparent.put("BaseColor2", 20);
        abTransparent.put("AB.png", 50);
        EFFECT_CONFIGS.put("ABTransparent", abTransparent);

        // ABTransparentMatte
        Map<String, Integer> abTransparentMatte = new LinkedHashMap<>();
        abTransparentMatte.put("BaseColor", 85);
        abTransparentMatte.put("Transparent.png", 70);
        abTransparentMatte.put("BaseColor2", 20);
        abTransparentMatte.put("AB.png", 40);
        abTransparentMatte.put("Matte.png", 60);
        EFFECT_CONFIGS.put("ABTransparentMatte", abTransparentMatte);

        // Ceylon
        Map<String, Integer> ceylon = new LinkedHashMap<>();
        ceylon.put("BaseColor", 95);
        ceylon.put("Metallic.png", 80);
        EFFECT_CONFIGS.put("Ceylon", ceylon);

        // CeylonCrystal
        Map<String, Integer> ceylonCrystal = new LinkedHashMap<>();
        ceylonCrystal.put("BaseColor", 85);
        ceylonCrystal.put("Metallic.png", 80);
        ceylonCrystal.put("BaseColor2", 20);
        EFFECT_CONFIGS.put("CeylonCrystal", ceylonCrystal);

        // Crystal
        Map<String, Integer> crystal = new LinkedHashMap<>();
        crystal.put("BaseColor", 90);
        crystal.put("Transparent_strong.png", 100);
        crystal.put("BaseColor2", 20);
        EFFECT_CONFIGS.put("Crystal", crystal);

        // Galvanized
        Map<String, Integer> galvanized = new LinkedHashMap<>();
        galvanized.put("BaseColor", 100);
        galvanized.put("Metallic_strong.png", 75);
        EFFECT_CONFIGS.put("Galvanized", galvanized);

        // GalvanizedFrosted
        Map<String, Integer> galvanizedFrosted = new LinkedHashMap<>();
        galvanizedFrosted.put("BaseColor", 100);
        galvanizedFrosted.put("Metallic_strong.png", 80);
        galvanizedFrosted.put("BaseColor2", 20);
        galvanizedFrosted.put("Matte.png", 50);
        EFFECT_CONFIGS.put("GalvanizedFrosted", galvanizedFrosted);

        // GalvanizedMatte
        Map<String, Integer> galvanizedMatte = new LinkedHashMap<>();
        galvanizedMatte.put("BaseColor", 100);
        galvanizedMatte.put("Metallic_strong.png", 90);
        galvanizedMatte.put("BaseColor2", 20);
        galvanizedMatte.put("Matte.png", 60);
        EFFECT_CONFIGS.put("GalvanizedMatte", galvanizedMatte);

        // Lined
        Map<String, Integer> lined = new LinkedHashMap<>();
        lined.put("BaseColor", 90);
        lined.put("Lined.png", 80);
        lined.put("BaseColor2", 20);
        EFFECT_CONFIGS.put("Lined", lined);

        // LinedCrystal
        Map<String, Integer> linedCrystal = new LinkedHashMap<>();
        linedCrystal.put("BaseColor", 90);
        linedCrystal.put("Lined.png", 80);
        linedCrystal.put("BaseColor2", 25);
        linedCrystal.put("Transparent_strong.png", 70);
        EFFECT_CONFIGS.put("LinedCrystal", linedCrystal);

        // LinedCrystalSparkling
        Map<String, Integer> linedCrystalSparkling = new LinkedHashMap<>();
        linedCrystalSparkling.put("BaseColor", 90);
        linedCrystalSparkling.put("Lined_strong.png", 80);
        linedCrystalSparkling.put("BaseColor2", 20);
        linedCrystalSparkling.put("Opaque.png", 40);
        EFFECT_CONFIGS.put("LinedCrystalSparkling", linedCrystalSparkling);

        // LinedLuster
        Map<String, Integer> linedLuster = new LinkedHashMap<>();
        linedLuster.put("BaseColor", 90);
        linedLuster.put("Lined.png", 80);
        linedLuster.put("BaseColor2", 20);
        linedLuster.put("Metallic.png", 70);
        EFFECT_CONFIGS.put("LinedLuster", linedLuster);

        // LinedLusterCrystal
        Map<String, Integer> linedLusterCrystal = new LinkedHashMap<>();
        linedLusterCrystal.put("BaseColor", 90);
        linedLusterCrystal.put("Lined.png", 80);
        linedLusterCrystal.put("BaseColor2", 20);
        linedLusterCrystal.put("Transparent.png", 50);
        EFFECT_CONFIGS.put("LinedLusterCrystal", linedLusterCrystal);

        // LinedLusterSparkling
        Map<String, Integer> linedLusterSparkling = new LinkedHashMap<>();
        linedLusterSparkling.put("BaseColor", 90);
        linedLusterSparkling.put("Lined_strong.png", 85);
        linedLusterSparkling.put("BaseColor2", 20);
        linedLusterSparkling.put("Transparent_strong.png", 70);
        EFFECT_CONFIGS.put("LinedLusterSparkling", linedLusterSparkling);

        // LinedSparkling
        Map<String, Integer> linedSparkling = new LinkedHashMap<>();
        linedSparkling.put("BaseColor", 90);
        linedSparkling.put("Lined.png", 80);
        linedSparkling.put("BaseColor2", 40);
        linedSparkling.put("Metallic.png", 70);
        EFFECT_CONFIGS.put("LinedSparkling", linedSparkling);

        // Luminous
        Map<String, Integer> luminous = new LinkedHashMap<>();
        luminous.put("BaseColor", 100);
        luminous.put("Metallic.png", 80);
        luminous.put("BaseColor2", 20);
        luminous.put("AB.png", 80);
        EFFECT_CONFIGS.put("Luminous", luminous);

        // Luster
        Map<String, Integer> luster = new LinkedHashMap<>();
        luster.put("BaseColor", 100);
        luster.put("Transparent_strong.png", 75);
        EFFECT_CONFIGS.put("Luster", luster);

        // LusterCrystal
        Map<String, Integer> lusterCrystal = new LinkedHashMap<>();
        lusterCrystal.put("BaseColor", 90);
        lusterCrystal.put("Transparent_strong.png", 85);
        lusterCrystal.put("BaseColor2", 20);
        EFFECT_CONFIGS.put("LusterCrystal", lusterCrystal);

        // Matte
        Map<String, Integer> matte = new LinkedHashMap<>();
        matte.put("BaseColor", 100);
        matte.put("Matte.png", 90);
        matte.put("BaseColor2", 20);
        EFFECT_CONFIGS.put("Matte", matte);

        // MatteCrystal
        Map<String, Integer> matteCrystal = new LinkedHashMap<>();
        matteCrystal.put("BaseColor", 90);
        matteCrystal.put("Transparent_strong.png", 70);
        matteCrystal.put("BaseColor2", 30);
        matteCrystal.put("Matte.png", 70);
        EFFECT_CONFIGS.put("MatteCrystal", matteCrystal);

        // Metallic
        Map<String, Integer> metallic = new LinkedHashMap<>();
        metallic.put("BaseColor", 100);
        metallic.put("Metallic.png", 80);
        EFFECT_CONFIGS.put("Metallic", metallic);

        // MetallicAB
        Map<String, Integer> metallicAB = new LinkedHashMap<>();
        metallicAB.put("BaseColor", 100);
        metallicAB.put("Metallic.png", 70);
        metallicAB.put("BaseColor2", 25);
        metallicAB.put("AB.png", 70);
        EFFECT_CONFIGS.put("MetallicAB", metallicAB);

        // MetallicABMatte
        Map<String, Integer> metallicABMatte = new LinkedHashMap<>();
        metallicABMatte.put("BaseColor", 100);
        metallicABMatte.put("Metallic.png", 80);
        metallicABMatte.put("BaseColor2", 30);
        metallicABMatte.put("AB.png", 60);
        metallicABMatte.put("Matte.png", 45);
        EFFECT_CONFIGS.put("MetallicABMatte", metallicABMatte);

        // MetallicLinedSparkling
        Map<String, Integer> metallicLinedSparkling = new LinkedHashMap<>();
        metallicLinedSparkling.put("BaseColor", 90);
        metallicLinedSparkling.put("Lined_strong.png", 85);
        metallicLinedSparkling.put("BaseColor2", 25);
        metallicLinedSparkling.put("Transparent_strong.png", 80);
        EFFECT_CONFIGS.put("MetallicLinedSparkling", metallicLinedSparkling);

        // MetallicMatte
        Map<String, Integer> metallicMatte = new LinkedHashMap<>();
        metallicMatte.put("BaseColor", 100);
        metallicMatte.put("Metallic.png", 75);
        metallicMatte.put("BaseColor2", 35);
        metallicMatte.put("Matte.png", 55);
        EFFECT_CONFIGS.put("MetallicMatte", metallicMatte);

        // MetallicSatin
        Map<String, Integer> metallicSatin = new LinkedHashMap<>();
        metallicSatin.put("BaseColor", 100);
        metallicSatin.put("Metallic.png", 75);
        metallicSatin.put("BaseColor2", 30);
        metallicSatin.put("Satin.png", 60);
        EFFECT_CONFIGS.put("MetallicSatin", metallicSatin);

        // Opaque
        Map<String, Integer> opaque = new LinkedHashMap<>();
        opaque.put("BaseColor", 100);
        opaque.put("Opaque.png", 90);
        opaque.put("BaseColor2", 15);
        EFFECT_CONFIGS.put("Opaque", opaque);

        // OpaqueCeylon
        Map<String, Integer> opaqueCeylon = new LinkedHashMap<>();
        opaqueCeylon.put("BaseColor", 100);
        opaqueCeylon.put("Transparent_strong.png", 85);
        opaqueCeylon.put("BaseColor2", 25);
        opaqueCeylon.put("Opaque.png", 35);
        EFFECT_CONFIGS.put("OpaqueCeylon", opaqueCeylon);

        // OpaqueFrosted
        Map<String, Integer> opaqueFrosted = new LinkedHashMap<>();
        opaqueFrosted.put("BaseColor", 100);
        opaqueFrosted.put("Transparent_strong.png", 85);
        opaqueFrosted.put("BaseColor2", 30);
        opaqueFrosted.put("Matte.png", 55);
        EFFECT_CONFIGS.put("OpaqueFrosted", opaqueFrosted);

        // OpaqueGlazed
        Map<String, Integer> opaqueGlazed = new LinkedHashMap<>();
        opaqueGlazed.put("BaseColor", 100);
        opaqueGlazed.put("Transparent_strong.png", 85);
        opaqueGlazed.put("BaseColor2", 25);
        EFFECT_CONFIGS.put("OpaqueGlazed", opaqueGlazed);

        // OpaqueLuster
        Map<String, Integer> opaqueLuster = new LinkedHashMap<>();
        opaqueLuster.put("BaseColor", 100);
        opaqueLuster.put("Transparent_strong.png", 80);
        opaqueLuster.put("BaseColor2", 10);
        EFFECT_CONFIGS.put("OpaqueLuster", opaqueLuster);

        // OpaqueMatte
        Map<String, Integer> opaqueMatte = new LinkedHashMap<>();
        opaqueMatte.put("BaseColor", 100);
        opaqueMatte.put("Transparent.png", 80);
        opaqueMatte.put("Matte.png", 55);
        opaqueMatte.put("BaseColor2", 15);
        EFFECT_CONFIGS.put("OpaqueMatte", opaqueMatte);

        // OpaqueMatteGlazed
        Map<String, Integer> opaqueMatteGlazed = new LinkedHashMap<>();
        opaqueMatteGlazed.put("BaseColor", 100);
        opaqueMatteGlazed.put("Transparent_strong.png", 80);
        opaqueMatteGlazed.put("Matte.png", 55);
        opaqueMatteGlazed.put("BaseColor2", 15);
        EFFECT_CONFIGS.put("OpaqueMatteGlazed", opaqueMatteGlazed);

        // OpaqueSatin
        Map<String, Integer> opaqueSatin = new LinkedHashMap<>();
        opaqueSatin.put("BaseColor", 100);
        opaqueSatin.put("Opaque.png", 30);
        opaqueSatin.put("BaseColor2", 30);
        opaqueSatin.put("Satin.png", 80);
        EFFECT_CONFIGS.put("OpaqueSatin", opaqueSatin);

        // Satin
        Map<String, Integer> satin = new LinkedHashMap<>();
        satin.put("BaseColor", 95);
        satin.put("Satin_strong.png", 70);
        satin.put("BaseColor2", 10);
        EFFECT_CONFIGS.put("Satin", satin);

        // SilverLined
        Map<String, Integer> silverLined = new LinkedHashMap<>();
        silverLined.put("BaseColor", 90);
        silverLined.put("Lined_strong.png", 90);
        silverLined.put("BaseColor2", 35);
        EFFECT_CONFIGS.put("SilverLined", silverLined);

        // SilverLinedCrystal
        Map<String, Integer> silverLinedCrystal = new LinkedHashMap<>();
        silverLinedCrystal.put("BaseColor", 90);
        silverLinedCrystal.put("Lined_strong.png", 90);
        silverLinedCrystal.put("BaseColor2", 40);
        silverLinedCrystal.put("Transparent_strong.png", 25);
        EFFECT_CONFIGS.put("SilverLinedCrystal", silverLinedCrystal);

        // SilverLinedFrosted
        Map<String, Integer> silverLinedFrosted = new LinkedHashMap<>();
        silverLinedFrosted.put("BaseColor", 90);
        silverLinedFrosted.put("Lined_strong.png", 90);
        silverLinedFrosted.put("BaseColor2", 30);
        silverLinedFrosted.put("Matte.png", 60);
        EFFECT_CONFIGS.put("SilverLinedFrosted", silverLinedFrosted);

        // SilverLinedGlazed
        Map<String, Integer> silverLinedGlazed = new LinkedHashMap<>();
        silverLinedGlazed.put("BaseColor", 90);
        silverLinedGlazed.put("Lined_strong.png", 90);
        silverLinedGlazed.put("BaseColor2", 35);
        silverLinedGlazed.put("Transparent_strong.png", 60);
        EFFECT_CONFIGS.put("SilverLinedGlazed", silverLinedGlazed);

        // Transparent
        Map<String, Integer> transparent = new LinkedHashMap<>();
        transparent.put("BaseColor", 85);
        transparent.put("Transparent.png", 80);
        transparent.put("BaseColor2", 10);
        EFFECT_CONFIGS.put("Transparent", transparent);

        // TransparentCrystal
        Map<String, Integer> transparentCrystal = new LinkedHashMap<>();
        transparentCrystal.put("BaseColor", 85);
        transparentCrystal.put("Transparent_strong.png", 85);
        transparentCrystal.put("BaseColor2", 10);
        EFFECT_CONFIGS.put("TransparentCrystal", transparentCrystal);

        // TransparentFrosted
        Map<String, Integer> transparentFrosted = new LinkedHashMap<>();
        transparentFrosted.put("BaseColor", 85);
        transparentFrosted.put("Transparent.png", 85);
        transparentFrosted.put("BaseColor2", 25);
        transparentFrosted.put("Matte.png", 50);
        EFFECT_CONFIGS.put("TransparentFrosted", transparentFrosted);

        // TransparentLuster
        Map<String, Integer> transparentLuster = new LinkedHashMap<>();
        transparentLuster.put("BaseColor", 90);
        transparentLuster.put("Transparent_strong.png", 75);
        transparentLuster.put("BaseColor2", 15);
        EFFECT_CONFIGS.put("TransparentLuster", transparentLuster);

        // TransparentMatte
        Map<String, Integer> transparentMatte = new LinkedHashMap<>();
        transparentMatte.put("BaseColor", 85);
        transparentMatte.put("Transparent.png", 90);
        transparentMatte.put("BaseColor2", 30);
        transparentMatte.put("Matte.png", 55);
        EFFECT_CONFIGS.put("TransparentMatte", transparentMatte);

        // TransparentSatin
        Map<String, Integer> transparentSatin = new LinkedHashMap<>();
        transparentSatin.put("BaseColor", 85);
        transparentSatin.put("Satin.png", 90);
        transparentSatin.put("BaseColor2", 20);
        EFFECT_CONFIGS.put("TransparentSatin", transparentSatin);

        // Dodaj kolejne efekty zgodnie z potrzebami...
    }


}
