package com.example.mahmoud_fetouh_task

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mahmoud_fetouh_task.adapter.ListAdapter
import com.example.mahmoud_fetouh_task.model.Data
import com.example.mahmoud_fetouh_task.webservice.RetrofitWebService
import com.example.mahmoud_fetouh_task.webservice.response.ListResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var listAdapter: ListAdapter? = null
    private val data: ArrayList<Data> = arrayListOf()
    var pastVisibleItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var page = 1
    var loadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getList()
        var mLayoutManager = LinearLayoutManager(
                this,
                RecyclerView.VERTICAL, false
        )
        listAdapter = ListAdapter(
                this, data
        )
        rec.layoutManager = mLayoutManager
        rec.adapter = listAdapter
        srl.setOnRefreshListener {
            page = 1
            loadMore = true
            listAdapter?.data?.clear()
            listAdapter?.notifyDataSetChanged()

            getList()

        }
        rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (loadMore) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loadMore = false
                            page++
                            getList()
                            progressBar?.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
    }

    private fun getList() {
        RetrofitWebService.getService().getList("http://demo1286023.mockable.io/api/v1/cars?page=" + page).enqueue(object : Callback<ListResponse> {
            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
            }

            override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
            ) {
                if (response.body()?.status == 1)
                    response.body()?.data?.let { data.addAll(it) }
                listAdapter?.notifyDataSetChanged()
                srl.isRefreshing = false
                progressBar?.visibility = View.GONE

            }

        })
    }
}