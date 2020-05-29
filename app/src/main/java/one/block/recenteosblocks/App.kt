package one.block.recenteosblocks

import android.app.Application
import one.block.recenteosblocks.di.apolloClient
import one.block.recenteosblocks.di.blockDetailViewModelModule
import one.block.recenteosblocks.di.blockGraphQlDataSource
import one.block.recenteosblocks.di.blockGraphQlMapper
import one.block.recenteosblocks.di.blockRepositoryModule
import one.block.recenteosblocks.di.dbModule
import one.block.recenteosblocks.di.eosApiModule
import one.block.recenteosblocks.di.homeViewModelModule
import one.block.recenteosblocks.di.httpClient
import one.block.recenteosblocks.di.listViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    homeViewModelModule,
                    listViewModelModule,
                    blockDetailViewModelModule,
                    blockRepositoryModule,
                    blockGraphQlDataSource,
                    blockGraphQlMapper,
                    dbModule,
                    eosApiModule,
                    apolloClient,
                    httpClient
                )
            )
        }
    }
}