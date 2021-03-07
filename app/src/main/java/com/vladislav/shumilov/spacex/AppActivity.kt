package vladislav.shumilov.spacex

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.vladislav.shumilov.core_ui.ui.activity.SingleActivity
import com.vladislav.shumilov.core_ui.utils.isTabletDevice
import com.vladislav.shumilov.spacex.R
import dagger.android.support.DaggerAppCompatActivity

internal class AppActivity : DaggerAppCompatActivity(),
    SingleActivity {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)

        if (!isTabletDevice(this)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId?.let { itemId ->
            if (itemId == android.R.id.home) {
                popBackStack()
                return false
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun getNavigationController() = navController
}
