package com.sigmapool.app.screens.miningProfit

import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.PostDto

class PostItemMapper : SimpleMapper<PostDto, PostItemViewModel>() {

    override fun map(input: PostDto): PostItemViewModel = PostItemViewModel(input)

}