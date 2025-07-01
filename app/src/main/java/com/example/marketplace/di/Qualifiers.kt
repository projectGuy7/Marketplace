package com.example.marketplace.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiWithLoginInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiWithoutInterceptor