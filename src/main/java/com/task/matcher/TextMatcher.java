package com.task.matcher;

import com.task.model.OffsetResult;
import com.task.reader.FileReader;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextMatcher {

    private final String key;

    public TextMatcher(String keysFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        new FileReader().readLines(0, 6, keysFileName)
                .stream()
                .flatMap(it -> Arrays.stream(it.getRight().split(","))
                        .collect(Collectors.toList()).stream())
                .forEach(key -> stringBuilder.append("\\b").append(key).append("\\b").append("|"));

        this.key = stringBuilder.substring(0, stringBuilder.toString().length() - 1);

    }

    public CompletableFuture<List<OffsetResult>> matchAsync(Pair<Integer, String> source) {
        return CompletableFuture.supplyAsync(() -> this.match(source.getRight(), source.getLeft()));
    }

    private List<OffsetResult> match(String source, int line) {
        List<OffsetResult> result = new ArrayList<>();
        Matcher match = Pattern.compile(this.key)
                .matcher(source);
        while (match.find()) {
            int offsetStart = match.start();
            result.add(new OffsetResult(match.group(), line, offsetStart));
        }
        return result;
    }
}
