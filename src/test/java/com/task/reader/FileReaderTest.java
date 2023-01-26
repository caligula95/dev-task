package com.task.reader;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.task.Main.SOURCE_FILE_NAME;
import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    private final FileReader fileReader = new FileReader();

    @Test
    void readLinesInCorrectLineOrder() {
        List<Pair<Integer, String>> pairs = fileReader.readLines(0, 10000, SOURCE_FILE_NAME);
        assertNotNull(pairs);
        assertEquals(pairs.get(0).getLeft(), 1);
        assertEquals(pairs.get(1).getLeft(), 2);
    }

    @Test
    void readLinesHandlesExceptionAndReturnsEmptyListIfFileNotFound() {
        List<Pair<Integer, String>> pairs = fileReader.readLines(0, 10000, "nonExist.txt");
        assertNotNull(pairs);
        assertEquals(pairs.size(), 0);
    }
}