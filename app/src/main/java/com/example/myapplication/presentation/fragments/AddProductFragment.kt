package com.example.myapplication.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddProductBinding
import com.example.myapplication.presentation.activity.MainActivity
import com.example.myapplication.presentation.adapter.ImageAdapter
import com.example.myapplication.presentation.adapter.ProductAdapter
import com.example.myapplication.presentation.di.Injector
import com.example.myapplication.presentation.viewmodel.AddProductViewModel
import com.example.myapplication.presentation.viewmodel.AddProductViewModelFactory
import com.example.myapplication.presentation.viewmodel.MainViewModelFactory
import java.security.Permission
import java.util.jar.Manifest
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddProductFragment : Fragment() {

    @set:Inject
    lateinit var factory: AddProductViewModelFactory
    private lateinit var viewModel: AddProductViewModel
    private lateinit var adapter: ImageAdapter
    private lateinit var binding: FragmentAddProductBinding
    private val read_permission = 101
    val uriList = ArrayList<Uri>()
    private lateinit var check: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_product, container, false)
        (requireActivity().application as Injector).createProductSubComponent()
            .inject2(this)
        viewModel = ViewModelProvider(this, factory).get(AddProductViewModel::class.java)
        initRecycler()
        checkPermission()
        onCLick()
        return binding.root
    }

    fun onCLick() {
        binding.addImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Images"), 1)
        }

        binding.addProductButton.setOnClickListener { click ->
            if (binding.rdGroup.checkedRadioButtonId == R.id.serviceCheck)
                check = "Service"
            else
                check = "Product"

            if (binding.edtProductName.text.isEmpty()) {
                binding.edtProductName.error = "Enter Product Name"
            }
            if (binding.edtProductPrice.text.isEmpty()) {
                binding.edtProductPrice.error = "Enter Price"
            }
            if (binding.edtProductPrice.text.isEmpty()) {
                binding.edtProductRate.error = "Enter Tax"
            } else {
                val name = binding.edtProductName.text.toString()
                val price = binding.edtProductPrice.text.toString()
                val tax = binding.edtProductRate.text.toString()

                val responseLiveData = viewModel.addProduct(
                    name,
                    check,
                    price,
                    tax
                )
                responseLiveData.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        if (it.success) {

                            click.findNavController()
                                .navigate(R.id.action_addProductFragment_to_productsFragment)
                        }
                    } else {
                        Toast.makeText(activity, "No Data Available", Toast.LENGTH_LONG).show()
                    }
                })
            }


        }
    }

    fun checkPermission() {
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(

                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                read_permission
            )
        }
    }

    fun initRecycler() {
        binding.rvImages.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ImageAdapter(MainActivity(), uriList)
        binding.rvImages.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data?.clipData !== null) {
                val x = data.clipData!!.itemCount

                for (i in 0 until x) {
                    uriList.add(data.clipData!!.getItemAt(i).uri)
                }

            } else if (data?.data != null) {
                val imageUrl = data.data!!
                uriList.add(imageUrl)
            }
            adapter.notifyDataSetChanged()
        }

    }


}