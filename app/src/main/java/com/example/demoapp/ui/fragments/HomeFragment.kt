package com.example.demoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoapp.adapter.ListItemAdapter
import com.example.demoapp.databinding.FragmentHomeBinding
import com.example.demoapp.enums.ListType
import com.example.demoapp.model.ListItem

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(LayoutInflater.from(requireContext()))
    }
    private val fakeList = mutableListOf<ListItem>()
    private val fakeImage = "https://img.gamemonetize.com/667o4xx4ql9sy291ciivb2obotgut3kz/512x384.jpg"
    private val adapterHorizontal = ListItemAdapter()
    private val adapterVertical = ListItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initAdapter()
    }

    private fun init() {
        fakeList.clear()
        fakeList.addAll(
            mutableListOf(
                ListItem(
                    id = 1,
                    title = "Technology",
                    heading = "Steps Into Tomorrow",
                    description = "Exploring Spatial Computing’s Impact On Industries And The Metaverse!",
                    date = "2024-06-16",
                    image = fakeImage
                ),
                ListItem(
                    id = 2,
                    title = "Technology",
                    heading = "Steps Into Tomorrow",
                    description = "Exploring Spatial Computing’s Impact On Industries And The Metaverse!",
                    date = "2024-06-16",
                    image = fakeImage
                ),
                ListItem(
                    id = 3,
                    title = "Technology",
                    heading = "Steps Into Tomorrow",
                    description = "Exploring Spatial Computing’s Impact On Industries And The Metaverse!",
                    date = "2024-06-16",
                    image = fakeImage
                ),
                ListItem(
                    id = 4,
                    title = "Technology",
                    heading = "Steps Into Tomorrow",
                    description = "Exploring Spatial Computing’s Impact On Industries And The Metaverse!",
                    date = "2024-06-16",
                    image = fakeImage
                )
            )
        )
    }

    private fun initAdapter() {
        binding.rvTop.adapter = adapterHorizontal
        binding.rvBottom.adapter = adapterVertical
        adapterHorizontal.setItems(fakeList, ListType.HORIZONTAL)
        adapterVertical.setItems(fakeList, ListType.VERTICAL)
    }
}
