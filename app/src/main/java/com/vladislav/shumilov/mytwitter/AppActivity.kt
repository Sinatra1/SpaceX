package vladislav.shumilov.mytwitter

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.vladislav.shumilov.mytwitter.AppRouter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

internal class AppActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var appRouter: AppRouter

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)

        appRouter.showActivity()

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId?.let { itemId ->
            if (itemId == android.R.id.home) {
                navController.popBackStack()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
