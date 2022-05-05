package com.vladislav.shumilov.launch_ui.ui.list

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import com.vladislav.shumilov.launch_ui.R
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.whenever
import org.mockito.quality.Strictness

private const val MISSION_ICON_TRANSITION_NAME = "missionIcon"

class LaunchesListViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var interactor: LaunchInteractor

    @Mock
    private lateinit var resources: Resources

    @Mock
    private lateinit var launchList: List<LaunchForList>

    private lateinit var viewModel: LaunchesListViewModel

    @Before
    fun setUp() {
        whenever(
            resources.getString(R.string.launches_mission_icon_transition_name)
        ).thenReturn(MISSION_ICON_TRANSITION_NAME)

        viewModel = LaunchesListViewModel(interactor, resources)
    }

    @Test
    fun `If get launches then inProcess will be true`() {
        viewModel.getLaunchesForList()

        Assert.assertTrue("inProcess should be true", viewModel.getInProcess().value!!)
    }

    @Test
    fun `If get launches then the progress in the center will be shown`() {
        viewModel.getLaunchesForList()

        Assert.assertTrue("the progress in the center should be shown", viewModel.isShownCenterProgress.get())
    }
}