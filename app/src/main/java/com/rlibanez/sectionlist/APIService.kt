package com.rlibanez.sectionlist

import com.rlibanez.sectionlist.data.Section
import com.rlibanez.sectionlist.data.SectionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {

    @GET("api/sections")
    suspend fun getAllSections(@Query("start") start: String): List<Section>

    @GET
    suspend fun getSections(@Url name: String): Response<SectionsResponse>

}