package com.health.test.mvvm.koin.ui.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.BR
import com.base.BaseActivity
import com.health.test.R
import com.health.test.databinding.ActivityShowDetailsBindingImpl
import com.health.test.mvvm.koin.adapter.WeatherReportAdapter
import com.health.test.mvvm.koin.adapter.ItemClickListener
import com.health.test.mvvm.koin.model.weather.WeatherReport
import com.health.test.retrofit.ProgressDialogLoader
import com.health.test.utils.Utils
import com.utils.Logger
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class DetailsActivity : BaseActivity<ActivityShowDetailsBindingImpl, DetailsActivityViewModel>(),
        DetailsActivityNavigator, ItemClickListener {
    private val homeViewModel: DetailsActivityViewModel by viewModel()
    private var toolbar: Toolbar? = null
    var progressBar: ProgressBar? = null
    private var rv_weather: RecyclerView? = null

    companion object {
        val logger = Logger.getLogger(DetailsActivity::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_show_details

    override fun getBindingVariable(): Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    private fun initUI() {
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        rv_weather = findViewById<View>(R.id.rv_weather) as RecyclerView
        rv_weather?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_weather?.layoutManager = layoutManager
        val resId = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(this, resId)
        rv_weather?.setLayoutAnimation(animation)

        if (Utils.checkInternetConnection(Objects.requireNonNull(this))) {
            showProgress()
            progressBar?.visibility = View.VISIBLE
            getViewModel().getDataFromServer("29.797703932918143", "77.47141108465448", "ae905ac872f2219493514849af1c407e")
        } else {
            Toast.makeText(this, getString(R.string.check_internet), Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share_story -> {

                true
            }
            else ->                 // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
        }
    }


    override fun updateUI(savedInstanceState: Bundle?) {
        homeViewModel.setNavigator(this)
        toolbar?.let {
            setUpToolBar(it.id, true)
        }
        initUI()
    }

    override fun getViewModel(): DetailsActivityViewModel = homeViewModel


    private fun updateToolBarTitle(title: String) {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar?.let {
            it.title = title
        }
        supportActionBar?.title = title
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            toolbar?.setNavigationOnClickListener {
                // displayInterstitial()
                finish()
            }
        }

    }

    override fun hideProgress() {
        ProgressDialogLoader.progressdialogDismiss()
    }

    override fun setData(weatherApiResponse: ArrayList<WeatherReport>) {
        try {
            progressBar?.visibility = View.GONE
            if (weatherApiResponse.size > 0) {
                val adapter = WeatherReportAdapter(this, weatherApiResponse)
                rv_weather?.adapter = adapter
                adapter.setClickListener(this)
            } else {
                Toast.makeText(this, getString(R.string.no_data_found_message), Toast.LENGTH_SHORT).show()
            }
        } catch (ee: Exception) {
            ee.printStackTrace()
        }
    }

    fun showProgress() {
        ProgressDialogLoader.progressDialogCreation(this, getString(R.string.loading))
    }

    override fun onClick(view: View?, position: Int) {
    }


}
