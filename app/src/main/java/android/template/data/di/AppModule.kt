package android.template.data.di

import android.template.data.Repository
import android.template.data.RepositoryImpl
import android.template.data.erasmulApi.DataModule
import android.template.data.erasmulApi.DataModuleImpl
import android.template.ui.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.dsl.bind

val appModule = module{
    viewModelOf(::FeedViewModel)
//    viewModelOf(::)
//    viewModelOf(::)

    singleOf(::RepositoryImpl) bind Repository::class
    singleOf(::DataModuleImpl) bind DataModule::class

}