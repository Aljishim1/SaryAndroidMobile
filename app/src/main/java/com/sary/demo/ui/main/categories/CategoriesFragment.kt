package com.sary.demo.ui.main.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sary.demo.data.models.entity.CatalogData
import com.sary.demo.databinding.FragmentCategoriesBinding
import com.sary.demo.ui.main.categories.adapter.*
import com.sary.demo.ui.main.categories.adapter.ByBusinessAdapter
import com.sary.demo.ui.main.categories.adapter.CatalogAdapter
import com.sary.demo.ui.main.categories.adapter.CircleAdapter
import com.sary.demo.ui.main.categories.adapter.PartnerAdapter
import com.sary.demo.ui.main.categories.adapter.TopProductAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoriesFragment : Fragment() {

    private val viewModel: CategoriesViewModel by sharedViewModel()

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var circleAdapter: CircleAdapter
    private val circleData = ArrayList<CatalogData>()

    private lateinit var partnerAdapter: PartnerAdapter
    private val partnerData = ArrayList<CatalogData>()

    private lateinit var topProductAdapter: TopProductAdapter
    private val topProductData = ArrayList<CatalogData>()

    private lateinit var categoryTwoGridAdapter: CatalogAdapter
    private val categoryTwoGridData = ArrayList<CatalogData>()

    private lateinit var categoryFourGridAdapter: CatalogAdapter
    private val categoryFourGridData = ArrayList<CatalogData>()

    private lateinit var byBusinessGridAdapter: ByBusinessAdapter
    private val byBusinessGridData = ArrayList<CatalogData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryTitleText: TextView = binding.categoryTitleText
        val partnerTitleText: TextView = binding.partnerTitleText
        val topProductTitleText: TextView = binding.topProductTitleText
        val byBusinessTypeRecyclerTitleText: TextView = binding.byBusinessTypeRecyclerTitleText
        val circlesRecyclerView: RecyclerView = binding.CirclesRecyclerView
        val partnerRecyclerView: RecyclerView = binding.PartnerRecyclerView
        val topProductRecyclerView: RecyclerView = binding.topProductRecyclerView
        val categoryTwoGridRecyclerView: RecyclerView = binding.categoryTwoGridRecyclerView
        val categoryFourGridRecyclerView: RecyclerView = binding.categoryFourGridRecyclerView
        val byBusinessTypeRecyclerView: RecyclerView = binding.byBusinessTypeRecyclerView

        circlesRecyclerView.layoutManager =GridLayoutManager(activity, 4)
        partnerRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        topProductRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        categoryTwoGridRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        categoryFourGridRecyclerView.layoutManager = GridLayoutManager(activity, 4)
        byBusinessTypeRecyclerView.layoutManager = GridLayoutManager(activity, 3)

        viewModel.fetchCatalogs()
        viewModel.fetchBanners()
        viewModel.catalogAPILiveData.observe(viewLifecycleOwner) {
            for (category in it.result) {
                if (category.id == 3) {
                    if (circleData.isEmpty()) {
                        for (circle in category.data) {
                            circleData.add(circle)
                        }
                    }
                    circleAdapter = CircleAdapter(circleData)
                    circlesRecyclerView.adapter = circleAdapter;
                }
                if (category.id == 171) {
                    partnerTitleText.text = category.title + " \uD83D\uDE0E"
                    if (partnerData.isEmpty()) {
                        for (partner in category.data) {
                            partnerData.add(partner)
                        }
                    }
                    partnerAdapter = PartnerAdapter(partnerData)
                    partnerRecyclerView.adapter = partnerAdapter;
                }
                if (category.id == 194) {
                    // top products
                    topProductTitleText.text = category.title + " \uD83D\uDED2"
                    if (topProductData.isEmpty()) {
                        for (top in category.data) {
                            topProductData.add(top)
                        }
                    }
                    topProductAdapter = TopProductAdapter(topProductData)
                    topProductRecyclerView.adapter = topProductAdapter;
                }
                if (category.id == 147) {
                    categoryTitleText.text = category.title + " \uD83D\uDED2"
                    if (categoryTwoGridData.isEmpty()) {
                        for (twoGrid in category.data) {
                            categoryTwoGridData.add(twoGrid)
                        }
                    }
                    categoryTwoGridAdapter = CatalogAdapter(categoryTwoGridData)
                    categoryTwoGridRecyclerView.adapter = categoryTwoGridAdapter;
                }
                if (category.id == 148) {
                    if (categoryFourGridData.isEmpty()) {
                        for (twoGrid in category.data) {
                            categoryFourGridData.add(twoGrid)
                        }
                    }
                    categoryFourGridAdapter = CatalogAdapter(categoryFourGridData)
                    categoryFourGridRecyclerView.adapter = categoryFourGridAdapter;
                }
                if (category.id == 13) {
                    byBusinessTypeRecyclerTitleText.text = category.title
                    if (byBusinessGridData.isEmpty()) {
                        for (twoGrid in category.data) {
                            byBusinessGridData.add(twoGrid)
                        }
                    }
                    byBusinessGridAdapter = ByBusinessAdapter(byBusinessGridData)
                    byBusinessTypeRecyclerView.adapter = byBusinessGridAdapter;
                }
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

