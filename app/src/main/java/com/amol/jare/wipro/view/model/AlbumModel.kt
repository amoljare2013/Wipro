package com.amol.jare.wiproapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AlbumModel {
    @SerializedName("results")
    @Expose
    private var results: Results? = null

    fun getResults(): Results? {
        return results
    }

    fun setResults(results: Results?) {
        this.results = results
    }

}

class Results {
    @SerializedName("opensearch:Query")
    @Expose
    var opensearchQuery: OpensearchQuery? = null

    @SerializedName("opensearch:totalResults")
    @Expose
    var opensearchTotalResults: String? = null

    @SerializedName("opensearch:startIndex")
    @Expose
    var opensearchStartIndex: String? = null

    @SerializedName("opensearch:itemsPerPage")
    @Expose
    var opensearchItemsPerPage: String? = null

    @SerializedName("albummatches")
    @Expose
    var albummatches: Albummatches? = null

    @SerializedName("@attr")
    @Expose
    var attr: Attr? = null

}

class Album {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("artist")
    @Expose
    var artist: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("image")
    @Expose
    var image: List<Image>? = null

    @SerializedName("streamable")
    @Expose
    var streamable: String? = null

    @SerializedName("mbid")
    @Expose
    var mbid: String? = null
}

class Albummatches {
    @SerializedName("album")
    @Expose
    var album: List<Album>? = null

}

class Attr {
    @SerializedName("for")
    @Expose
    var `for`: String? = null

}

class Image {
    @SerializedName("#text")
    @Expose
    var text: String? = null

    @SerializedName("size")
    @Expose
    var size: String? = null

}

class OpensearchQuery {
    @SerializedName("#text")
    @Expose
    var text: String? = null

    @SerializedName("role")
    @Expose
    var role: String? = null

    @SerializedName("searchTerms")
    @Expose
    var searchTerms: String? = null

    @SerializedName("startPage")
    @Expose
    var startPage: String? = null

}


