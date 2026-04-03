package com.example.spotifyclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumItem {

    @JsonProperty("album_type")
    private String albumType;

    @JsonProperty("total_tracks")
    private int totalTracks;

    private String id;
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    private String type;
    private String uri;
    private List<ArtistItem> artists;
}
