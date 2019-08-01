package com.sigmapool.common.listLibrary.loader

enum class ItemsLoaderState {
    Idle, // do nothing
    Refreshing, // load first portion of data
    Loading, // load next portion of data
    Error  //some error occurred
}
