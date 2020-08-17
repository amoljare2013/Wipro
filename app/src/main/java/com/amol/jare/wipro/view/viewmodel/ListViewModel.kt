package com.amol.jare.wiproapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.amol.jare.wiproapp.model.Album
import com.amol.jare.wiproapp.model.ListRepository

class ListViewModel : BaseViewModel() {

    val albumList = MutableLiveData<List<Album>>()
    fun fetchRepoList(album: String) {
        ListRepository.getInstance().getVal(album)
        try {
            dataLoading.value = true
            ListRepository.getInstance().getRepoList { isSuccess, response ->
                dataLoading.value = false
                if (isSuccess) {
                    if (response != null && response.getResults()!!.albummatches?.album?.size!! > 0) {
                        albumList.postValue(response?.getResults()!!.albummatches?.album)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}