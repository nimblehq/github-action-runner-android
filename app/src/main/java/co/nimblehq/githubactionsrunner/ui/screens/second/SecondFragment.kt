package co.nimblehq.githubactionsrunner.ui.screens.second

import android.Manifest
import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import co.nimblehq.githubactionsrunner.databinding.FragmentSecondBinding
import co.nimblehq.githubactionsrunner.domain.data.Data
import co.nimblehq.githubactionsrunner.extension.loadImage
import co.nimblehq.githubactionsrunner.extension.subscribeOnClick
import co.nimblehq.githubactionsrunner.ui.base.BaseFragment
import co.nimblehq.githubactionsrunner.ui.helpers.handleVisualOverlaps
import co.nimblehq.githubactionsrunner.ui.screens.MainNavigator
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding>() {

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var rxPermissions: RxPermissions

    private val viewModel by viewModels<SecondViewModel>()
    private val args: SecondFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSecondBinding
        get() = { inflater, container, attachToParent ->
            FragmentSecondBinding.inflate(inflater, container, attachToParent)
        }

    override fun setupView() {
        with(binding) {
            btOpenCamera
                .subscribeOnClick(::requestCamera)
                .addToDisposables()

            btOpenPost
                .subscribeOnClick(viewModel::openPost)
                .addToDisposables()
        }
    }

    override fun handleVisualOverlaps() {
        with(binding) {
            btOpenCamera.handleVisualOverlaps()
            btOpenPost.handleVisualOverlaps()
        }
    }

    override fun bindViewModel() {
        viewModel.navigator bindTo navigator::navigate
        viewModel.data bindTo ::bindData

        viewModel.input.dataFromIntent(args.bundle.data)
    }

    private fun requestCamera() {
        rxPermissions
            .requestEach(Manifest.permission.CAMERA)
            .subscribe(::handleCameraPermission)
            .addToDisposables()
    }

    private fun handleCameraPermission(permission: Permission) {
        when {
            permission.granted -> {
                startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            }
            permission.shouldShowRequestPermissionRationale -> {
                // Deny
                toaster.display("Permission camera denied")
            }
            else -> {
                // Deny and never ask again
                toaster.display("Permission camera never ask again")
            }
        }
    }

    private fun bindData(data: Data) {
        with(data) {
            with(binding) {
                tvSecondTitle.text = title
                tvSecondAuthor.text = author
                ivSecondThumbnail.loadImage(thumbnail)
            }
        }
    }
}
