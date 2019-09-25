package com.sigmapool.api.blog

import android.content.Context
import com.sigmapool.api.R
import com.sigmapool.api.hasConnection
import com.sigmapool.common.managers.IBlogManager
import com.sigmapool.common.models.BlogDto
import com.sigmapool.common.models.ManagerResult

internal class StubBlogManager(val context: Context) : IBlogManager {

    override suspend fun getBlogs(
        page: Int,
        perPage: Int,
        lang: String
    ): ManagerResult<ArrayList<BlogDto>> {
        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }

        return ManagerResult(
            arrayListOf(
                BlogDto(
                    "https://www.google.com/",
                    "https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg"
                ),
                BlogDto(
                    "https://www.google.com/",
                    "https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg"
                ),
                BlogDto(
                    "https://www.google.com/",
                    "https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png"
                )
            )
        )
    }
}