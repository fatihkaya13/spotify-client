package com.example.spotifyclient.client;

import com.example.spotifyclient.dto.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class SpotifyTokenClient {

    private final RestClient tokenRestClient;
    private final CacheManager cacheManager;
    private final String clientId;
    private final String clientSecret;

    public SpotifyTokenClient(
            CacheManager cacheManager,
            @Value("${spotify.client-id}") String clientId,
            @Value("${spotify.client-secret}") String clientSecret) {
        this.cacheManager = cacheManager;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenRestClient = RestClient.builder()
                .baseUrl("https://accounts.spotify.com")
                .build();
    }

    @Cacheable("spotifyToken")
    public String getAccessToken() {
        log.info("Fetching new Spotify access token from API");

        String body = "grant_type=client_credentials"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret;

        TokenResponse response = tokenRestClient.post()
                .uri("/api/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .body(TokenResponse.class);

        log.info("Successfully fetched and cached new Spotify access token");
        return response.getAccessToken();
    }

    public String getAccessTokenManual() {
        Cache cache = cacheManager.getCache("spotifyToken");
        String cachedToken = cache.get("token", String.class);

        if (cachedToken != null) {
            log.info("[MANUAL] Using cached Spotify access token");
            return cachedToken;
        }

        log.info("[MANUAL] Fetching new Spotify access token from API");

        String body = "grant_type=client_credentials"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret;

        TokenResponse response = tokenRestClient.post()
                .uri("/api/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .body(TokenResponse.class);

        String token = response.getAccessToken();
        cache.put("token", token);

        log.info("[MANUAL] Successfully fetched and cached new Spotify access token");
        return token;
    }
}
