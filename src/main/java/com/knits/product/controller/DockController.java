package com.knits.product.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.DockDto;
import com.knits.product.service.DockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a REST API controller class to handle dock related data
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/dock")
public class DockController {

    private final DockService dockService;

    /**
     *
     * @return REST API Endpoint to get all dock data
     */
    @GetMapping
    public ResponseEntity<List<DockDto>> getAllDockData() {
        return ResponseEntity.ok().body(dockService.getAllDocks());
    }
}