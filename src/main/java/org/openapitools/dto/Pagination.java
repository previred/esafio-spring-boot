package org.openapitools.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pagination {

    private Integer page;
    private Integer totalElements;
    private Integer totalPages;
    private boolean hasPrevious;
    private boolean hasNext;
    private List<Integer> pageNumbers;
}
