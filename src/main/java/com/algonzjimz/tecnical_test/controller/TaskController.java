package com.algonzjimz.tecnical_test.controller;

import com.algonzjimz.tecnical_test.dto.ApiResponseDto;
import com.algonzjimz.tecnical_test.model.Task;
import com.algonzjimz.tecnical_test.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name = "Task Controller", description = "Controlador para gestionar tareas")
@SecurityRequirement(name = "BearerAuth")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Obtener tareas", description = "Devuelve todas las tareas del sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida con éxito")
    public ResponseEntity<ApiResponseDto<List<Task>>> listTask() {
        return ResponseEntity.ok(ApiResponseDto.success("Lista de tareas obtenida con éxito", taskService.getAll()));
    }

    @PostMapping
    @Operation(summary = "Crear tarea", description = "Crea una nueva tarea.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarea creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity<ApiResponseDto<Task>> createTask(@RequestBody Task newTask) {
        return ResponseEntity.status(201).body(ApiResponseDto.created("Tarea creada con éxito", taskService.create(newTask)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tarea", description = "Actualiza una tarea existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea actualizada"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    public ResponseEntity<ApiResponseDto<Task>> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask)
                .map(task -> ResponseEntity.ok(ApiResponseDto.success("Tarea actualizada", task)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(HttpStatus.NOT_FOUND, "Tarea no encontrada")));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tarea por ID", description = "Devuelve los detalles de una tarea específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    public ResponseEntity<ApiResponseDto<Task>> getById(@PathVariable Long id) {
        return taskService.getById(id)
                .map(task -> ResponseEntity.ok(ApiResponseDto.success("Tarea encontrada", task)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(HttpStatus.NOT_FOUND, "Tarea no encontrada")));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tarea", description = "Elimina una tarea específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarea eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    public ResponseEntity<ApiResponseDto<String>> delete(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteTById(id);
        if (isDeleted) {
            return ResponseEntity.ok(ApiResponseDto.success("Tarea eliminada correctamente", "Tarea con ID " + id + " eliminada"));
        } else {
            return ResponseEntity.status(404).body(ApiResponseDto.error(HttpStatus.NOT_FOUND, "Tarea no encontrada"));
        }
    }
}
