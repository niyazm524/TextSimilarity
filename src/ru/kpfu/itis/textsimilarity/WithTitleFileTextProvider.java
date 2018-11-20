package ru.kpfu.itis.textsimilarity;

import java.io.File;

public class WithTitleFileTextProvider implements TextProvider {
    private TextProvider textProvider;
    private File file;

    public WithTitleFileTextProvider(File f) {
        this.file = f;
        textProvider = new FileTextProvider(f);
    }

    @Override
    public String getText() {
        return file.getName() + " " + textProvider.getText();
    }
}
