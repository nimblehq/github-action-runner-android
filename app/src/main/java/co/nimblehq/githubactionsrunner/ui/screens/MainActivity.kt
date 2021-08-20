package co.nimblehq.githubactionsrunner.ui.screens

import android.view.LayoutInflater
import androidx.activity.viewModels
import co.nimblehq.githubactionsrunner.databinding.ActivityMainBinding
import co.nimblehq.githubactionsrunner.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        { inflater -> ActivityMainBinding.inflate(inflater) }

    private val viewModel by viewModels<MainViewModel>()

    override fun bindViewModel() {}
}
