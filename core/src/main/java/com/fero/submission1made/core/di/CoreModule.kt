package com.fero.submission1made.core.di

import androidx.room.Room
import com.fero.submission1made.core.data.MealRepository
import com.fero.submission1made.core.data.source.local.LocalDataSource
import com.fero.submission1made.core.data.source.local.room.MealDatabase
import com.fero.submission1made.core.data.source.remote.RemoteDataSource
import com.fero.submission1made.core.data.source.remote.network.ApiService
import com.fero.submission1made.core.domain.repository.IMealRepository
import com.fero.submission1made.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<MealDatabase>().mealDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("fero".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MealDatabase::class.java, "Meal.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "www.themealdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/pz7CjjOO6yeiHWrcJ+RWljKC2pBYw+9O7XwRIl7HLn8=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMealRepository> {
        MealRepository(
            get(),
            get(),
            get()
        )
    }
}

