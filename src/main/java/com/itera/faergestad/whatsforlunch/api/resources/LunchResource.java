package com.itera.faergestad.whatsforlunch.api.resources;

import com.itera.faergestad.whatsforlunch.api.domain.slack.SlackResponse;
import com.itera.faergestad.whatsforlunch.services.LunchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.itera.faergestad.whatsforlunch.util.SlackMapper.convertToSlackResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LunchResource {

    private final LunchService lunchService;

    @PostMapping("/")
    public ResponseEntity<SlackResponse> getTodaysLunch() {
        SlackResponse response = convertToSlackResponse(lunchService.getTodaysLunch());
        return ResponseEntity.ok(response);
    }
}
