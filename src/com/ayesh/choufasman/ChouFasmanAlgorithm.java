package com.ayesh.choufasman;

import java.util.HashMap;
import java.util.Map;

public class ChouFasmanAlgorithm {
    private final Map<Character, Double> helixPropensity = new HashMap<>();
    private final Map<Character, Double> sheetPropensity = new HashMap<>();
    private final Map<Character, Double> turnPropensity = new HashMap<>();

    // Initialize the propensity tables
    public ChouFasmanAlgorithm() {
        initializePropensityTables();
    }

    private void initializePropensityTables() {
        // Populate the helix, sheet, and turn propensities for each amino acid
        helixPropensity.put('A', 1.42);    sheetPropensity.put('A', 0.83);   turnPropensity.put('A', 0.66);
        helixPropensity.put('C', 0.70);    sheetPropensity.put('C', 1.19);   turnPropensity.put('C', 1.19);
        helixPropensity.put('D', 1.01);    sheetPropensity.put('D', 0.54);   turnPropensity.put('D', 1.46);
        helixPropensity.put('E', 1.51);    sheetPropensity.put('E', 0.37);   turnPropensity.put('E', 0.74);
        helixPropensity.put('F', 1.13);    sheetPropensity.put('F', 1.38);   turnPropensity.put('F', 0.60);
        helixPropensity.put('G', 0.57);    sheetPropensity.put('G', 0.75);   turnPropensity.put('G', 1.56);
        helixPropensity.put('H', 1.00);    sheetPropensity.put('H', 0.87);   turnPropensity.put('H', 0.95);
        helixPropensity.put('I', 1.08);    sheetPropensity.put('I', 1.60);   turnPropensity.put('I', 0.47);
        helixPropensity.put('K', 1.16);    sheetPropensity.put('K', 0.74);   turnPropensity.put('K', 1.01);
        helixPropensity.put('L', 1.21);    sheetPropensity.put('L', 1.30);   turnPropensity.put('L', 0.59);
        helixPropensity.put('M', 1.45);    sheetPropensity.put('M', 1.05);   turnPropensity.put('M', 0.60);
        helixPropensity.put('N', 0.67);    sheetPropensity.put('N', 0.89);   turnPropensity.put('N', 1.56);
        helixPropensity.put('P', 0.57);    sheetPropensity.put('P', 0.55);   turnPropensity.put('P', 1.52);
        helixPropensity.put('Q', 1.11);    sheetPropensity.put('Q', 1.10);   turnPropensity.put('Q', 0.98);
        helixPropensity.put('R', 0.98);    sheetPropensity.put('R', 0.93);   turnPropensity.put('R', 0.95);
        helixPropensity.put('S', 0.77);    sheetPropensity.put('S', 0.75);   turnPropensity.put('S', 1.43);
        helixPropensity.put('T', 0.83);    sheetPropensity.put('T', 1.19);   turnPropensity.put('T', 0.96);
        helixPropensity.put('V', 1.06);    sheetPropensity.put('V', 1.70);   turnPropensity.put('V', 0.50);
        helixPropensity.put('W', 1.08);    sheetPropensity.put('W', 1.37);   turnPropensity.put('W', 0.96);
        helixPropensity.put('Y', 0.69);    sheetPropensity.put('Y', 1.47);   turnPropensity.put('Y', 1.14);
    }


    // Method to predict secondary structure
    public String predictStructure(String sequence) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            char aa = sequence.charAt(i);
            if (!helixPropensity.containsKey(aa)) {
                result.append('C'); // 'C' for coil or undefined structure
                continue;
            }

            double helixScore = helixPropensity.get(aa);
            double sheetScore = sheetPropensity.get(aa);

            if (helixScore > 1.0) {
                result.append('H'); // H for helix
            } else if (sheetScore > 1.0) {
                result.append('E'); // E for sheet
            } else {
                result.append('C'); // C for coil or undefined
            }
        }
        return result.toString();
    }
}
