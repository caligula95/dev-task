package com.task;

import com.task.aggregator.ResultAggregator;
import com.task.matcher.TextMatcher;
import com.task.model.OffsetResult;
import com.task.reader.FileReader;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Main {

    public static final String SOURCE_FILE_NAME = "source.txt";
    public static final String KEYS_FILE_NAME = "keys.txt";

    private final ResultAggregator resultAggregator;
    private final FileReader fileReader;
    private final TextMatcher textMatcher;

    public Main() {
        this.resultAggregator = new ResultAggregator();
        this.fileReader = new FileReader();
        this.textMatcher = new TextMatcher(KEYS_FILE_NAME);
    }

    public Map<String, List<OffsetResult>> processData() {
        int start = 0;
        int step = 1000;
        List<Pair<Integer, String>> sourceLines = new ArrayList<>();
        List<Pair<Integer, String>> partialPairList = fileReader.readLines(start, step, SOURCE_FILE_NAME);
        while (!partialPairList.isEmpty()) {
            start = start + step;
            sourceLines.addAll(partialPairList);
            partialPairList = fileReader.readLines(start, step, SOURCE_FILE_NAME);
        }

        List<OffsetResult> result = sourceLines.stream().parallel()
                .map(textMatcher::matchAsync)
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return resultAggregator.aggregate(result);
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.processData());
    }
}
