package com.ayesh.choufasman;

public class ProteinSecondaryStructure {
    private final ChouFasmanAlgorithm algorithm;

    public ProteinSecondaryStructure() {
        this.algorithm = new ChouFasmanAlgorithm();
    }

    // Method to process the amino acid sequence and predict secondary structure
    public String predict(String sequence) {
        return algorithm.predictStructure(sequence);
    }
}