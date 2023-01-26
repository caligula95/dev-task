package com.task;

import com.task.model.OffsetResult;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {

    public Main main = new Main();

    @Test
    void processData() {
        Map<String, List<OffsetResult>> stringListMap = main.processData();
        assertNotNull(stringListMap);
    }
}