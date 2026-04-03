package com.example.spotifyclient.client;

import com.example.spotifyclient.dto.AlbumResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SpotifyAlbumClient {

    private final RestClient restClient;
    private final SpotifyTokenClient tokenClient;

    public SpotifyAlbumClient(RestClient restClient, SpotifyTokenClient tokenClient) {
        this.restClient = restClient;
        this.tokenClient = tokenClient;
    }

    public AlbumResponse getArtistAlbums(String artistId, int limit, int offset) {
        return restClient.get()
                .uri("/artists/{id}/albums?limit={limit}&offset={offset}", artistId, limit, offset)
                .header("Authorization", "Bearer " + tokenClient.getAccessToken())
                .retrieve()
                .body(AlbumResponse.class);
    }
}
