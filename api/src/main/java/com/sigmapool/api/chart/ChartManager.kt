package com.sigmapool.api.chart


import com.sigmapool.api.poolinfo.wrapManagerResult
import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IChartManager
import com.sigmapool.common.models.ChartDto
import com.sigmapool.common.models.ManagerResult


internal class ChartManager(serviceProvider: IApiServiceProvider) : IChartManager {

    private val chartService = serviceProvider.create(ChartApi::class.java)

    override suspend fun getChart(): ManagerResult<ChartDto> {
        return wrapManagerResult {

            val chart = this.chartService.getChart().payload!!
//            val data = ArrayList<SeriesDto>()
//
//            val cal = GregorianCalendar()
//            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            for(i in 0..50) {
//                val s = SeriesDto(df.format(cal.time).toString(), (1.7e18+Random().nextLong()/1000).toLong())
//                System.out.println("!!!" + s.time + " " + s.hashrate)
//
//                data.add(s)
//            }

            val chartDto = ChartDto(
                chart.series
//                data.toTypedArray()
            )

            chartDto
        }
    }

}
