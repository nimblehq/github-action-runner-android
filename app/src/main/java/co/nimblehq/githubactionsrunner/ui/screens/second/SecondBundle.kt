package co.nimblehq.githubactionsrunner.ui.screens.second

import android.os.Parcelable
import co.nimblehq.githubactionsrunner.domain.data.Data
import kotlinx.parcelize.Parcelize

@Parcelize
data class SecondBundle(
    val data: Data
) : Parcelable
