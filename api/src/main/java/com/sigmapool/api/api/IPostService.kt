package com.sigmapool.api.api

import com.sigmapool.api.models.Post

internal interface IPostService {
    suspend fun getPosts(offset:Int, limit:Int):List<Post>
}

