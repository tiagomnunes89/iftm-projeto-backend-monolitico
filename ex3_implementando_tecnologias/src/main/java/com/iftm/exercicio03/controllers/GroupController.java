package com.iftm.exercicio03.controllers;

import com.iftm.exercicio03.data.vo.GroupVO;
import com.iftm.exercicio03.services.GroupService;
import com.iftm.exercicio03.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
@Tag(name = "Group", description = "Endpoint for managing groups.")
public class GroupController {
    @Autowired
    GroupService service;

    // READ - HTTP GET
// Endpoint: http://localhost:8080/api/v1/group
    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(
            summary = "Find all groups.", description = "Find all groups.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = GroupVO.class))
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    public List<GroupVO> findAll() {
        return service.findAll();
    }

    // READ - HTTP GET
// Endpoint: http://localhost:8080/api/v1/group/ID
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(
            summary = "Find a group by ID.", description = "Find a group by ID.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = GroupVO.class)
                                    )
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public GroupVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // CREATE - HTTP POST
// Endpoint: http://localhost:8080/api/v1/group
    @PostMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(
            summary = "Create a group.", description = "Create a group.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = GroupVO.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public GroupVO save(@RequestBody GroupVO groupVO) {
        return service.save(groupVO);
    }

    // INSERT USERS - HTTP POST
// Endpoint: http://localhost:8080/api/v1/group/insert-users
    @PostMapping(value = "insert-users", consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(
            summary = "Insert users in a group.", description = "Insert users in a group.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = GroupVO.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public GroupVO insertUsers(@RequestBody GroupVO groupVO) {
        return service.insertUsers(groupVO);
    }

    // UPDATE - HTTP PUT
// Endpoint: http://localhost:8080/api/v1/group
    @PutMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(
            summary = "Update a group.", description = "Update a group.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = GroupVO.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public GroupVO update(@RequestBody GroupVO groupVO) {
        return service.update(groupVO);
    }

    // DELETE - HTTP DELETE
// Endpoint: http://localhost:8080/api/v1/group/ID
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletes a Group by ID.", description = "Delete a Group by ID.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
