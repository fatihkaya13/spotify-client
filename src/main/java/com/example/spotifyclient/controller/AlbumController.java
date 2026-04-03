package com.example.spotifyclient.controller;

import com.example.spotifyclient.dto.AlbumResponse;
import com.example.spotifyclient.service.AlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums/pink-floyd")
    public AlbumResponse getPinkFloydAlbums(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return albumService.getPinkFloydAlbums(limit, offset);
    }
}
