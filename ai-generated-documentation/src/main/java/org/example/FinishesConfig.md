![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## FinishesConfig Documentation

**1. Overview:**

The `FinishesConfig` class defines a set of configurations for various visual effects applied to objects in a system (likely a game or 3D rendering application). Each effect has specific properties like opacity, color blending, and image overlays. The configurations are stored as key-value pairs where the key is the effect name and the value is a map defining its parameters.

**2. Package/Module Name:**

org.example

**3. Class/File Name:**

FinishesConfig.java

**4. Detailed Documentation:**


* **`EFFECT_CONFIGS` (static final Map<String, Map<String, Integer>>):**
    - **Description:** A map containing all defined effect configurations. Each key represents an effect name (e.g., "Basic", "AB"), and the value is another map defining its specific parameters. 
    - **Parameters:** None
    - **Return Values:**  A static final Map of String to Map<String, Integer> representing all available effects and their configurations.

**5. Pseudo Code:**


```
// Class: FinishesConfig

// Static Initialization Block (executed once when the class is loaded)
  1. Create a new HashMap called EFFECT_CONFIGS.
  2. Define each effect configuration as a key-value pair in the EFFECT_CONFIGS map:
     * **Basic:** 
        * Key: "BaseColor" - Value: 100 (full opacity)
        * Key: "Transparent_strong.png" - Value: 65 (65% opacity)
        * ... Add other parameters as defined in the code.
     * **AB:**
        * Key: "BaseColor" - Value: 100 (full opacity)
        * Key: "AB.png" - Value: 80 (80% opacity)
        * ... Add other parameters as defined in the code.
     * ... Define configurations for all other effects similarly.

// Note: The pseudocode above only outlines the structure of the EFFECT_CONFIGS map. It does not include the logic for handling specific effect configurations or any potential user interactions.



```


**Dependencies and Libraries:**

The `FinishesConfig` class relies on the Java standard library's `HashMap` and `Map` interfaces to store and manage its configuration data. 

* **Java HashMap:**  Used to implement the `EFFECT_CONFIGS` map, allowing for efficient key-value storage and retrieval of effect configurations.



