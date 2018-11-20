package ml.bimdev.textsimilarity;

import ru.kpfu.itis.textsimilarity.TextAnalyzer;
import ru.kpfu.itis.textsimilarity.TextProvider;

import java.util.*;

public class CosineTextAnalyzer implements TextAnalyzer {
    @Override
    public double analyze(TextProvider te1, TextProvider te2) {
        String[] tokens1 = tokenize(te1.getText());
        String[] tokens2 = tokenize(te2.getText());
        List<String> uniques = uniquize(tokens1, tokens2);
        uniques.sort(String::compareTo);

        int[] vector1 = vectorize(uniques, tokens1);
        int[] vector2 = vectorize(uniques, tokens2);

        return calculate(vector1, vector2) ;       
    }
    
    private double calculate(int[] vectorA, int[] vectorB) {
        double numerator = 0, denom1 = 0, denom2 = 0;
        for (int i = 0; i < vectorA.length; i++) {
            numerator += vectorA[i] * vectorB[i];
            denom1 += vectorA[i] * vectorA[i];
            denom2 += vectorB[i] * vectorB[i];
        }

        return numerator / (Math.sqrt(denom1) * Math.sqrt(denom2));
    }

    private int[] vectorize(List<String> dictionary, String[] tokens) {
        int[] vector = new int[dictionary.size()];
        for(String word : tokens) {
            int index = dictionary.indexOf(word);
            vector[index] += 1;
        }

        return vector;
    }

    private List<String> uniquize(String[]... wordsArrays) {
        ArrayList<String> unique = new ArrayList<>();
        for(String[] wordsArray : wordsArrays) {
            for(String word : wordsArray) {
                if(!unique.contains(word))
                    unique.add(word);
            }
        }

        return unique;
    }

    private String[] tokenize(String text) {
        return text.toLowerCase().split("[\\s\\t\\n.!?,;:()]+");
    }
}
