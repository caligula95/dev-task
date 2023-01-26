package com.task.aggregator;

import com.task.model.OffsetResult;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ResultAggregatorTest {

    private final ResultAggregator resultAggregator = new ResultAggregator();

    @Test
    void aggregate() {
        List<OffsetResult> matchResult = List.of(
                new OffsetResult("Arthur", 1, 1),
                new OffsetResult("Arthur", 2, 4),
                new OffsetResult("James", 5, 3)
        );
        Map<String, List<OffsetResult>> aggregate = resultAggregator.aggregate(matchResult);
        assertNotNull(aggregate);
        assertEquals(2, aggregate.size());
        assertEquals(2, aggregate.get("Arthur").size());
    }

    @Test
    void aggregateEmptyResult() {

        Map<String, List<OffsetResult>> aggregate = resultAggregator.aggregate(List.of());
        assertNotNull(aggregate);
        assertEquals(0, aggregate.size());
    }
}