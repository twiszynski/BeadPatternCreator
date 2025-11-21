![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## FinishesConfig Documentation

**1. Overview:**

The `FinishesConfig` class defines a set of configurations for different visual effects applied to objects in a system (likely a game or similar application). Each effect is represented by a unique name, and its configuration consists of opacity values for various image layers and the base color. 

**2. Package/module name:** org.example

**3. Class/file name:** FinishesConfig.java

**4. Detailed Documentation:**


* **`EFFECT_CONFIGS` (static final Map<String, Map<String, Integer>>):**
    - **Description:** A map containing all defined effect configurations. Each key is the effect name (e.g., "Basic", "AB"), and the value is another map specifying opacity values for different image layers and the base color.

* **`static { ... }` block:**
    - **Description:** This block initializes the `EFFECT_CONFIGS` map with various predefined effects, each having a unique set of opacity values and a base color. 


**5. Pseudo Code:**



```
// Class: FinishesConfig

// Initialization of EFFECT_CONFIGS map
  1. Create an empty HashMap called EFFECT_CONFIGS to store effect configurations.
  2. Define various effects (e.g., "Basic", "AB", "Ceylon") as keys in the EFFECT_CONFIGS map.
  3. For each effect:
    - Create a new LinkedHashMap to store opacity values and base color for that effect.
    - Set opacity values for different image layers (e.g., "BaseColor", "Opaque.png", "AB.png") based on the desired effect.
    - Set the base color value for the effect.
    - Add this configuration map to the EFFECT_CONFIGS map using the effect name as the key.



```

**Note:** The pseudocode only covers the initialization of the `EFFECT_CONFIGS` map. It does not include any logic for accessing or using these configurations within other parts of the application. 


