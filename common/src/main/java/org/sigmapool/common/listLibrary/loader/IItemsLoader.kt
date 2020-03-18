package org.sigmapool.common.listLibrary.loader

interface IItemsLoader<Item> {
    suspend fun load(query:String, offset:Int, limit:Int): LoaderResult<List<Item>>
}

