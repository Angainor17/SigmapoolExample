package com.sigmapool.api.api

import com.sigmapool.api.models.Post
import kotlinx.coroutines.delay

internal class StubPostService:IPostService{

    private val items = List(200){
        Post(
            it.toLong(),
            "Title of post $it",
            "Some text for post $it. Not enough words.... Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
        )
    }

    override suspend fun getPosts(offset: Int, limit: Int): List<Post> {

        if(offset >= items.size){
            return emptyList()
        }

        delay(1000)

        return items.slice(IntRange(offset, offset+limit-1))
    }
}