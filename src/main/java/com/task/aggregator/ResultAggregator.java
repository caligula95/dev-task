package com.task.aggregator;

import com.task.model.OffsetResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultAggregator {

    public Map<String, List<OffsetResult>> aggregate(List<OffsetResult> match) {
        return match.stream()
                .collect(Collectors.groupingBy(OffsetResult::getKey));
    }
}
