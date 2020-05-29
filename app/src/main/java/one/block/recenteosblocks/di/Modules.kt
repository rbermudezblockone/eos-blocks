package one.block.recenteosblocks.di

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient
import one.block.recenteosblocks.data.datasources.BlockApiDataSource
import one.block.recenteosblocks.data.datasources.BlockGraphQlDataSource
import one.block.recenteosblocks.data.datasources.BlockRestDataSource
import one.block.recenteosblocks.data.datasources.mappers.BlockGraphQlMapper
import one.block.recenteosblocks.data.db.AppDatabase
import one.block.recenteosblocks.data.network.ApolloClientObject
import one.block.recenteosblocks.data.network.EosAPI
import one.block.recenteosblocks.data.network.GRAPHQL_URL
import one.block.recenteosblocks.data.network.NET_TIMEOUT_SECONDS
import one.block.recenteosblocks.data.repositories.BlockRepository
import one.block.recenteosblocks.ui.detail.BlockDetailViewModel
import one.block.recenteosblocks.ui.home.HomeViewModel
import one.block.recenteosblocks.ui.list.ListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val homeViewModelModule = module {
    viewModel {
        HomeViewModel()
    }
}

val listViewModelModule = module {
    viewModel {
        ListViewModel(get())
    }
}

val blockDetailViewModelModule = module {
    viewModel {
        BlockDetailViewModel(get())
    }
}

val blockRepositoryModule = module {
    single {
        BlockRepository(get())
    }
}

val blockRestDataSource = module {
    factory<BlockApiDataSource> {
        BlockRestDataSource(get(), get())
    }
}

val blockGraphQlDataSource = module {
    factory<BlockApiDataSource> {
        BlockGraphQlDataSource(get(), get(), get())
    }
}

val dbModule = module {
    single {
        AppDatabase(androidApplication())
    }
}

val eosApiModule = module {
    single {
        EosAPI()
    }
}

val apolloClient = module {
    single {
        ApolloClient.builder()
            .okHttpClient(get())
            .serverUrl(GRAPHQL_URL)
            .build()
    }
}

val httpClient = module {
    single {
        OkHttpClient().newBuilder()
            .connectTimeout(NET_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NET_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NET_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }
}

val blockGraphQlMapper = module {
    single {
        BlockGraphQlMapper()
    }
}