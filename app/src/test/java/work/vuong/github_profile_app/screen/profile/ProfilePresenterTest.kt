package work.vuong.github_profile_app.screen.profile

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import githubapi.GetUserQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import work.vuong.github_profile_app.common.network.GitHubApiService

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ProfilePresenterTest {

    @Mock
    lateinit var mockView: ProfileView

    @Mock
    lateinit var mockGithubApiService: GitHubApiService

    private lateinit var presenter: ProfilePresenter

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Default)
        MockitoAnnotations.initMocks(this)
        presenter = ProfilePresenter(mockGithubApiService)
    }

    @Test
    fun onFirstViewAttach_withUserData_callsShowProfile() = runBlocking {
        val mockUser: GetUserQuery.User = mock()
        whenever(mockGithubApiService.fetchUser(any())).thenReturn(mockUser)

        presenter.attachView(mockView)

        verify(mockGithubApiService, times(1)).fetchUser(any())
        verify(mockView, times(1)).setRefreshing(true)
        verify(mockView, times(1)).showProfile(mockUser)
    }

    @Test
    fun onFirstViewAttach_withNull_callsShowProfile() = runBlocking {
        whenever(mockGithubApiService.fetchUser(any())).thenReturn(null)

        presenter.attachView(mockView)

        verify(mockGithubApiService, times(1)).fetchUser(any())
        verify(mockView, times(1)).setRefreshing(true)
        verify(mockView, times(1)).setRefreshing(false)
        verify(mockView, never()).showProfile(any())
        // TODO: 27/08/2021 when handling error handling add verify for .showError
    }

    @Test
    fun onRefresh_fetchesUser() = runBlocking {
        val mockUser: GetUserQuery.User = mock()
        whenever(mockGithubApiService.fetchUser(any())).thenReturn(mockUser)

        presenter.attachView(mockView)
        presenter.onRefresh()

        verify(mockGithubApiService, times(2)).fetchUser(any())
        verify(mockView, times(1)).setRefreshing(true)
        verify(mockView, times(2)).setRefreshing(false)
        verify(mockView, times(2)).showProfile(any())
    }

}