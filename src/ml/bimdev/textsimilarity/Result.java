package ml.bimdev.textsimilarity;

import ru.kpfu.itis.textsimilarity.TextProvider;

public class Result implements Comparable<Result> {
    private TextProvider provider;
    private Double similarity;
    public int id;

    public Result(int id, TextProvider provider, Double similarity) {
        this.provider = provider;
        this.similarity = similarity;
        this.id = id;
    }

    public TextProvider getProvider() {
        return provider;
    }

    public Double getSimilarity() {
        return similarity;
    }

    @Override
    public int compareTo(Result other) {
        return similarity.compareTo(other.similarity);
    }

    @Override
    public String toString() {
        return String.format("Similarity: %s, Text: %s\n\n", Math.round(similarity*10000)/100 + "%", provider.getText());
    }
}
