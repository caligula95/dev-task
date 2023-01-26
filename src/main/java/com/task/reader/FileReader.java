package com.task.reader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Slf4j
public class FileReader {

    public List<Pair<Integer, String>> readLines(int startLine, int step, String file) {
        AtomicInteger lineNumber = new AtomicInteger(startLine);
        List<Pair<Integer, String>> lineNumberValuePairList = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            lines.skip(startLine)
                    .limit(step)
                    .forEach(l -> lineNumberValuePairList.add(Pair.of(lineNumber.incrementAndGet(), l)));
            return lineNumberValuePairList;
        } catch (IOException e) {
            log.error("Exception while reading lines: {}", e.getMessage());
        }
        return Collections.emptyList();
    }
}
