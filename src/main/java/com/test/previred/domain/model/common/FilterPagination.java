package com.test.previred.domain.model.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class FilterPagination {

    Integer page;
    Integer size;

}
