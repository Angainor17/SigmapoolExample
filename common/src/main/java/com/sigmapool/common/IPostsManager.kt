package com.sigmapool.common

import com.sigmapool.common.models.PostDto

interface IPostsManager {
    suspend fun getPosts(offset: Int, limit: Int): ManagerResult<List<PostDto>>
}