package com.amol.jare.wipro.view

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amol.jare.wipro.R
import com.amol.jare.wipro.view.adapter.ListAdapter
import com.amol.jare.wiproapp.model.Album
import com.amol.jare.wiproapp.viewmodel.ListViewModel
import com.ankit.jare.utils.NetworkConnecitity
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    lateinit var viewModel: ListViewModel
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val decoration = DividerItemDecoration(requireContext(), ClipDrawable.HORIZONTAL)
        repo_list_rv.addItemDecoration(decoration)

        viewModel = ViewModelProviders.of(this@FirstFragment).get(ListViewModel::class.java)

        btn_search.setOnClickListener(View.OnClickListener {
            if (edtSearch.text.length > 0) {
                callAPI(edtSearch.text.toString())
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.album_string),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun callAPI(text: String) {
        try {
            if (NetworkConnecitity.isNetworkAvailable(requireContext())) {
                linProgress.visibility = View.VISIBLE
                viewModel.fetchRepoList(text)
                setupObservers()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.network_message),
                    Toast.LENGTH_SHORT
                ).show()
            }

        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }


    private fun setupObservers() {
        viewModel?.albumList?.observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                setupAdapter(it)
                linProgress.visibility = View.GONE
                edtSearch.setText(" ")
            } else {
                txtMsg.visibility = View.VISIBLE
            }
        })
    }

    private fun setupAdapter(list: List<Album>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.repo_list_rv)
        adapter = ListAdapter(list, { album: Album -> albumItemClicked(album) })
        if (recyclerView != null) {
            val layoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = layoutManager
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
        var itemDecoration: RecyclerView.ItemDecoration? = null
        if (recyclerView != null) {
            while (recyclerView.itemDecorationCount > 0 && (recyclerView.getItemDecorationAt(0)
                    ?.let { itemDecoration = it }) != null
            ) {
                recyclerView.removeItemDecoration(itemDecoration!!)
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun albumItemClicked(albumItem: Album) {
        showDialog(albumItem)
    }

    fun showDialog(albumItem: Album) {
        val dialogFragment = DetailsDialogFragmen(this)
        fragmentManager?.let {
            val args = Bundle()
            args.putString("albumName", albumItem.name)
            args.putString("albumAtistName", albumItem.artist)
            args.putString("albumImageUrl", albumItem.image?.get(3)?.text)
            dialogFragment.arguments = args
            dialogFragment.show(it, "DetalsFragment")
        }
    }

}
