package one.block.recenteosblocks.data.network

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

const val NET_TIMEOUT_SECONDS = 10L
const val GRAPHQL_URL = "https://apollo-b1-example.herokuapp.com/graphql"

object ApolloClientObject {
    private val httpClient = OkHttpClient().newBuilder()
        .connectTimeout(NET_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(NET_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(NET_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    val apolloClient = ApolloClient.builder()
        .okHttpClient(httpClient)
        .serverUrl(GRAPHQL_URL)
        .build()
}