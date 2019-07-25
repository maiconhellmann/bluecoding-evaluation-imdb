package com.hellmann.bluecoding.data.remote

import com.hellmann.bluecoding.data.remote.api.ServerApi
import com.hellmann.bluecoding.data.remote.model.ArticlePayload
import com.hellmann.bluecoding.data.remote.model.ArticlesPayload
import com.hellmann.bluecoding.data.remote.source.RemoteDataSource
import com.hellmann.bluecoding.data.remote.source.RemoteDataSourceImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class RemoteDataSourceTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var serverApi: ServerApi

    @Before
    fun prepare() {
        serverApi = mock(ServerApi::class.java)
        remoteDataSource = RemoteDataSourceImpl(serverApi)
    }

    @Test
    fun `article list is empty`() {
        `when`(serverApi.fetchArticles()).then {
            Single.just(
                ArticlesPayload(
                    "ok", 0, emptyList()))
        }
        with(remoteDataSource.getMovies().test()) {
            assertValue {
                it.isEmpty()
            }
            assertValueCount(1)
        }
    }

    @Test
    fun `article list not empty`() {
        `when`(serverApi.fetchArticles()).then {
            Single.just(
                ArticlesPayload(
                    "ok", 1, listOf(ArticlePayload("", ""))))
        }

        with(remoteDataSource.getMovies().test()) {
            assertValue { it.isNotEmpty() }
            assertValueCount(1)
        }
    }
}