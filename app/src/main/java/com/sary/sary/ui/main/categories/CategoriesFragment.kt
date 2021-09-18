package com.sary.sary.ui.main.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sary.sary.databinding.FragmentCategoriesBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoriesFragment : Fragment() {

    private val viewModel: CategoriesViewModel by sharedViewModel()

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        val textView: TextView = binding.textHome

        viewModel.fetchBanners()
        viewModel.bannersAPILiveData.observe(requireActivity(), {
            Log.d("data", it.toString())
        })
    }

    private fun categorySub() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

