package com.task.matcher;

import com.task.model.OffsetResult;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.task.Main.KEYS_FILE_NAME;
import static org.junit.jupiter.api.Assertions.*;

class TextMatcherTest {

    private final TextMatcher textMatcher = new TextMatcher(KEYS_FILE_NAME);

    @Test
    void matchAsync() throws ExecutionException, InterruptedException {
        List<OffsetResult> result = textMatcher
                .matchAsync(Pair.of(2, "by Sir Arthur Conan Doyle")).get();

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getKey(), "Arthur");
    }

    @Test
    void matchAsyncEmptyString() throws ExecutionException, InterruptedException {
        List<OffsetResult> result = textMatcher
                .matchAsync(Pair.of(2, "")).get();

        assertNotNull(result);
        assertEquals(result.size(), 0);
    }
}