package com.example.spotifyclient.controller;

import com.example.spotifyclient.dto.AlbumResponse;
import com.example.spotifyclient.service.AlbumService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums/pink-floyd")
    public AlbumResponse getPinkFloydAlbums(
            @RequestParam(defaultValue = "5") @Min(1) @Max(10) int limit,
            @RequestParam(defaultValue = "0") @Min(0) int offset) {
        return albumService.getPinkFloydAlbums(limit, offset);
    }
}
