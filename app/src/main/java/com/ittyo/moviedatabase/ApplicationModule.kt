package com.ittyo.moviedatabase

import com.ittyo.moviedatabase.repository.MovieDatabaseRepository
import com.ittyo.moviedatabase.repository.MovieRepository
import com.ittyo.moviedatabase.repository.remote.MovieDatabaseService
import com.ittyo.moviedatabase.repository.remote.MovieDatabaseServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideMovieDatabaseService(): MovieDatabaseService {
        return MovieDatabaseServiceFactory.build("6753d9119b9627493ae129f3c3c99151", BuildConfig.DEBUG)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(movieDatabaseService: MovieDatabaseService): MovieRepository {
        return MovieDatabaseRepository(movieDatabaseService)
    }
}