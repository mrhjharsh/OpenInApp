package com.example.openinappassignment.fragment

import LinkViewModel
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openinappassignment.R
import com.example.openinappassignment.utils.Utils
import com.example.openinappassignment.adapter.LinksAdapter
import com.example.openinappassignment.databinding.FragmentLinksBinding
import com.example.openinappassignment.model.Data
import com.example.openinappassignment.model.Link
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class LinksFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentLinksBinding
    lateinit var response: LinkViewModel
    lateinit var adapterLinks: LinksAdapter
    lateinit var data: Data
    lateinit var supportNumber: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLinksBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        binding.rlTopLinks.setOnClickListener(this)
        binding.rlRecentLinks.setOnClickListener(this)
        binding.rlWhatapp.setOnClickListener(this)
        binding.txtGreetings.text = Utils().getGreeting()
        fetchData()
        setChart()
    }

    private fun setAdapter(list: List<Link>) {
        adapterLinks = LinksAdapter(list,requireActivity())
        binding.rvLinks.isNestedScrollingEnabled = false
        binding.rvLinks.setHasFixedSize(true)
        binding.rvLinks.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvLinks.adapter = adapterLinks
    }

    private fun fetchData() {
        response = ViewModelProvider(this)[LinkViewModel::class.java]
        response.getLiveData().observe(requireActivity()) { responseData ->
            binding.txtTodaysClick.text = responseData.today_clicks.toString()
            binding.txtTopLocation.text = responseData.top_location
            binding.txtTopSource.text = responseData.top_source
            supportNumber = responseData.support_whatsapp_number
            data = responseData.data
            setAdapter(data.top_links)
        }
    }

    private fun setChart() {

        // Create data entries
        val entries = listOf(
            Entry(0f, 2f),
            Entry(1f, 4f),
            Entry(2f, 6f),
            Entry(3f, 8f),
            Entry(4f, 10f)
        )

        // Create a dataset and give it a type
        val dataSet = LineDataSet(entries, "Label").apply {
            color = resources.getColor(R.color.purple_500, null) // Line color
            valueTextColor = resources.getColor(R.color.black, null) // Value text color
        }

        // Create a LineData object and set it to the chart
        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        // Optional: Customize chart appearance
        binding.lineChart.apply {
            description.text = ""
            setDrawGridBackground(false)
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.rlTopLinks) {
            binding.txtTopLinks.setTextColor(Color.WHITE)
            binding.txtRecentLinks.setTextColor(Color.parseColor("#999CA0"))
            binding.cvRecentLinks.setCardBackgroundColor(Color.parseColor("#F5F5F5"))
            binding.cvTopLinks.setCardBackgroundColor(Color.parseColor("#0E6FFF"))
            setAdapter(data.top_links)

        }
        if (v.id == R.id.rlRecentLinks) {
            binding.txtRecentLinks.setTextColor(Color.WHITE)
            binding.txtTopLinks.setTextColor(Color.parseColor("#999CA0"))
            binding.cvRecentLinks.setCardBackgroundColor(Color.parseColor("#0E6FFF"))
            binding.cvTopLinks.setCardBackgroundColor(Color.parseColor("#F5F5F5"))
            setAdapter(data.recent_links)

        }
        if (v.id == R.id.rlWhatapp) {
            Utils().openWhatsApp(requireActivity() ,supportNumber, "")

        }
    }





}