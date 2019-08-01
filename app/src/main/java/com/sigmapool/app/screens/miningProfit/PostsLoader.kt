package com.sigmapool.app.screens.miningProfit

import com.sigmapool.api.managers.PostsManager
import com.sigmapool.common.IPostsManager
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.LoaderResult
import com.sigmapool.common.models.PostDto

class PostsLoader : IItemsLoader<PostDto> {
    private val manager: IPostsManager = PostsManager()

    override suspend fun load(query: String, offset: Int, limit: Int): LoaderResult<List<PostDto>> {
        val result = manager.getPosts(offset, limit)

        return if (result.success) {
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }
}