package ir.moodz.omidpaysampleproject.di


import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.moodz.omidpaysampleproject.data.local.ProductDatabase
import ir.moodz.omidpaysampleproject.data.remote.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideProductApi(): ProductApi{
        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesProductDatabase(app: Application): ProductDatabase{
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            "productdb.db"
        ).build()
    }


}