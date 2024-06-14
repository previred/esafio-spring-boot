package com.desafio.spring.ec.ws.expose;

import com.desafio.spring.ec.ds.dto.StatusTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.desafio.spring.ec.ws.common.SwaggerConstants.STATUS_DESCRIPTION;
import static com.desafio.spring.ec.ws.common.SwaggerConstants.STATUS_TAG;

@Tag(name = STATUS_TAG, description = STATUS_DESCRIPTION)
@RequestMapping("/api/status")
public interface IStatusController {

    @Operation(summary = "API to get Status by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status found"),
            @ApiResponse(responseCode = "404", description = "Status not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<StatusTO> getStatusById(@PathVariable Long id) throws Exception;

    @Operation(summary = "API to get all Status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status found"),
            @ApiResponse(responseCode = "404", description = "Status not found")
    })
    @GetMapping
    ResponseEntity<List<StatusTO>> getAllStatus() throws Exception;
}
