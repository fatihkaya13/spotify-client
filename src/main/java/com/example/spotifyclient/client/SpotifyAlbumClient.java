package com.example.spotifyclient.client;

import com.example.spotifyclient.dto.AlbumResponse;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("spotifyAlbums")
    public AlbumResponse getArtistAlbums(String artistId, int limit, int offset) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/artists/{id}/albums")
                        .queryParam("limit", limit)
                        .queryParam("offset", offset)
                        .build(artistId))
                .header("Authorization", "Bearer " + tokenClient.getAccessToken())
                .retrieve()
                .body(AlbumResponse.class);
    }
}
