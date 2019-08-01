package com.sigmapool.api.managers

import com.sigmapool.api.api.IPostService
import com.sigmapool.common.IPostsManager
import com.sigmapool.common.ManagerResult
import com.sigmapool.common.models.PostDto
import org.kodein.di.generic.instance

class PostsManager() : IPostsManager {

    private val postsService: IPostService by managerKodein.instance()

    override suspend fun getPosts(offset: Int, limit: Int): ManagerResult<List<PostDto>> {
        return try {
            ManagerResult(
                postsService
                    .getPosts(offset, limit)
                    .map {
                        PostDto(
                            it.id,
                            it.title,
                            it.text
                        )
                    }
            )
        } catch (e: Throwable) {
            ManagerResult(error = e.message)
        }
    }
}