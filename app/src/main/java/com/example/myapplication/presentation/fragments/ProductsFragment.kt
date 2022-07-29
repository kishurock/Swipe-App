package com.example.myapplication.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.presentation.viewmodel.MainViewModelFactory
import com.example.myapplication.databinding.FragmentProductsBinding
import com.example.myapplication.presentation.viewmodel.MainViewModel
import com.example.myapplication.presentation.adapter.ProductAdapter
import com.example.myapplication.presentation.activity.MainActivity
import com.example.myapplication.presentation.di.Injector
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment() {
    @set:Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var binding: FragmentProductsBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false)
        (requireActivity().application as Injector).createProductSubComponent()
            .inject(this)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        initRecyclerViw()
        onClicks()
        return binding.root
    }

    private fun onClicks() {
        binding.btNewProduct.setOnClickListener{
            it.findNavController().navigate(R.id.action_productsFragment_to_addProductFragment)
        }
    }

    private fun initRecyclerViw() {
        binding.ProductRecycler.layoutManager = LinearLayoutManager(context)
        adapter = ProductAdapter(MainActivity())
        binding.ProductRecycler.adapter = adapter
        displayProducts()

    }
    private fun displayProducts() {
        binding.progressBar.visibility = View.VISIBLE
        val responseLiveData = viewModel.getProducts()
        responseLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(activity, "No Data Available", Toast.LENGTH_LONG).show()
            }
        })
    }

}