package hanz.coding.composemp.di

import hanz.coding.composemp.dependencies.MyRepository
import hanz.coding.composemp.dependencies.MyRepositoryImpl
import hanz.coding.composemp.dependencies.MyViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val shareModule = module {
    singleOf(::MyRepositoryImpl).bind<MyRepository>()
    viewModelOf(::MyViewModel)
}