package com.example.spotifyclient.service;

import com.example.spotifyclient.client.SpotifyAlbumClient;
import com.example.spotifyclient.dto.AlbumResponse;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private static final String PINK_FLOYD_ID = "0k17h0D3J5VfsdmQ1iZtE9";

    private final SpotifyAlbumClient albumClient;

    public AlbumService(SpotifyAlbumClient albumClient) {
        this.albumClient = albumClient;
    }

    public AlbumResponse getPinkFloydAlbums(int limit, int offset) {
        return albumClient.getArtistAlbums(PINK_FLOYD_ID, limit, offset);
    }
}
