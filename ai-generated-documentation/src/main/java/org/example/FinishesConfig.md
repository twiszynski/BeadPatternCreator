![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## FinishesConfig Documentation

**1. Overview:**

The `FinishesConfig` class defines a set of predefined configurations for various visual effects applied to surfaces in a 3D environment or similar application. Each configuration consists of a name, and a map of effect names (e.g., "BaseColor", "Opaque.png") mapped to their respective opacity values (represented as integers). These configurations are used to control the appearance of objects within the system.

**2. Package/module name:**

org.example

**3. Class/file name:**

FinishesConfig.java

**4. Class diagram name:**

FinishesConfig_diagram.svg

**5. Detailed Documentation:**

The `FinishesConfig` class utilizes a static map (`EFFECT_CONFIGS`) to store all available finish configurations. Each entry in the map is a named configuration (e.g., "Basic", "AB", "Opaque") with its corresponding effect settings. 

* **Static Map `EFFECT_CONFIGS`:** This LinkedHashMap stores all predefined finishes and their associated opacity values for different effects.  Each key represents a finish name, and the value is a LinkedHashMap containing effect names (e.g., "BaseColor", "Opaque.png") as keys and their corresponding opacity values (integers) as values.

**Example Configuration Entry:**

```java
Map<String, Integer> basic = new LinkedHashMap<>();
basic.put("BaseColor", 100);          // pełna krycie koloru bazowego
basic.put("Transparent_strong.png", 65); // nakładka metaliczna z przezroczystością 60%
basic.put("BaseColor2", 50);          // pełna krycie koloru bazowego
EFFECT_CONFIGS.put("Basic", basic);
```

**Accessing Configurations:**

To access a specific finish configuration, use the `EFFECT_CONFIGS` map and its key (finish name). For example:

```java
Map<String, Integer> basicFinish = EFFECT_CONFIGS.get("Basic");
int baseColorOpacity = basicFinish.get("BaseColor");
```

**6. Pseudo Code:**



```
// Class: FinishesConfig

// Method: getFinishConfiguration(finishName)
  1. Check if the finishName exists in the EFFECT_CONFIGS map.
    - If not, return null or throw an exception indicating the configuration is not found.
  2. Retrieve the corresponding LinkedHashMap from the EFFECT_CONFIGS map using the finishName as the key.
  3. Return the retrieved LinkedHashMap containing the finish configuration settings.

// Method: getOpacityForEffect(finishName, effectName)
  1. Get the finish configuration for the given finishName using getFinishConfiguration().
  2. Check if the effectName exists in the retrieved configuration map.
    - If not, return -1 or throw an exception indicating the effect is not found in this configuration.
  3. Retrieve the opacity value associated with the effectName from the configuration map.
  4. Return the retrieved opacity value.

// Method: applyFinishToSurface(surface, finishName)
  1. Get the finish configuration for the given finishName using getFinishConfiguration().
  2. Iterate through each effect name and its corresponding opacity value in the configuration map.
    - Apply the effect to the surface with the retrieved opacity value. 
      - This may involve setting material properties, textures, or other visual attributes based on the specific effects defined in the configuration.

// Example Usage:
  1. Get the "Opaque" finish configuration:
     Map<String, Integer> opaqueFinish = FinishesConfig.getFinishConfiguration("Opaque");
  2. Get the opacity value for the "BaseColor" effect in the "Opaque" finish:
     int baseColorOpacity = FinishesConfig.getOpacityForEffect("Opaque", "BaseColor");
  3. Apply the "AB" finish to a surface:
     FinishesConfig.applyFinishToSurface(surface, "AB"); 



```

**Note:** The pseudocode provides a general outline and should be adapted based on the specific implementation details of your application.


