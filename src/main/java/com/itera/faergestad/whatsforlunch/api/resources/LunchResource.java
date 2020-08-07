package com.itera.faergestad.whatsforlunch.api.resources;

import com.itera.faergestad.whatsforlunch.api.domain.Lunch;
import com.itera.faergestad.whatsforlunch.services.LunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lunch")
@RequiredArgsConstructor
public class LunchResource {

    private final LunchService lunchService;

    @GetMapping
    public ResponseEntity<Lunch> getTodaysLunch() {
        return ResponseEntity.of(lunchService.getTodaysLunch());
    }
}
