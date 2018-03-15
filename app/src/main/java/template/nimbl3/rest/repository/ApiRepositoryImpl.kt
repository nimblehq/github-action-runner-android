package template.nimbl3.rest.repository

import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import template.nimbl3.rest.api.ApiService
import template.nimbl3.rest.response.ExampleResponse

class ApiRepositoryImpl(private val apiService: ApiService,private val gson: Gson) : ApiRepository {

    override fun getExampleData(): Flowable<ExampleResponse> {
        return apiService
            .getExampleData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
