package org.sigmapool.sigmapool.screens.workers.viewModel

import android.graphics.Color
import androidx.core.text.isDigitsOnly
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.common.utils.*
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider

class WorkersListItemVM(
    resProvider: IResProvider,
    hashrate: Long,
    hashrate24h: Long,
    val name: String,
    val isOnline: Boolean

) : BaseItemViewModel {

    val hashrate: CharSequence = createHashrateString(hashrate, resProvider)
    val hashrate24h: CharSequence = createHashrateString(hashrate24h, resProvider)

    companion object {
        val itemType = WorkersListItemVM::class.hashCode()
        val headerItemType = -1
    }

    override val itemViewType: Int = itemType
    override fun areItemsTheSame(item: BaseItemViewModel): Boolean = false
    override fun areContentsTheSame(item: BaseItemViewModel): Boolean = false

    private fun createHashrateString(value: Long, resProvider: IResProvider): CharSequence {
        val result = formatLongValue(value, FLOAT_PATTERN)

        return formatValueWithPostfix(
            result.beforeLastChar() + " ",
            if (result.lastChar().isDigitsOnly()) "" else result.lastChar() + resProvider.getString(R.string.hashrate_per_second),
            Color.rgb(133, 133, 133)
        )
    }
}