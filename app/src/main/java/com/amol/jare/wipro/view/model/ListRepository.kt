package com.amol.jare.wiproapp.model

import com.ankit.jare.model.api.ApiClient
import com.ankit.jare.utils.Constants.Companion.APIKEY
import com.ankit.jare.utils.Constants.Companion.format
import com.ankit.jare.utils.Constants.Companion.method
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

class ListRepository() {

    private var inputVal = "";

    fun getVal(inputValue: String) {
        inputVal = inputValue;
    }

    fun getRepoList(onResult: (isSuccess: Boolean, response: AlbumModel?) -> Unit) {
        try {
            ApiClient.instance.getList(method, inputVal, APIKEY, format)
                .enqueue(object : Callback<AlbumModel> {
                    override fun onResponse(
                        call: Call<AlbumModel>?,
                        response: Response<AlbumModel>?
                    ) {
                        if (response != null && response.isSuccessful) {
                            onResult(true, response.body()!!)
                        } else {
                            onResult(false, null)
                        }

                    }

                    override fun onFailure(call: Call<AlbumModel>?, t: Throwable?) {
                        onResult(false, null)
                    }

                })
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    companion object {
        private var INSTANCE: ListRepository? = null
        fun getInstance() = INSTANCE
            ?: ListRepository().also {
                INSTANCE = it
            }
    }
}