package com.rlibanez.sectionlist

import com.rlibanez.sectionlist.data.Section
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    fun getAllSections(): Response<List<Section>>

    @GET
    fun getSection(@Url name: String): Response<Section>

}