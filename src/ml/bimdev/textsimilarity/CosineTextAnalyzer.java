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

        double numerator = 0;
        for (int i = 0; i < vector1.length; i++) {
            numerator += vector1[i] * vector2[i];
        }
        double denom1 = 0;
        for (int elem : vector1) {
            denom1 += elem * elem;
        }
        double denom2 = 0;
        for (int elem : vector2) {
            denom2 += elem * elem;
        }

//        System.out.println(Arrays.toString(uniques.toArray()));
//        System.out.println(Arrays.toString(vector1));
//        System.out.println(Arrays.toString(vector2));

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
