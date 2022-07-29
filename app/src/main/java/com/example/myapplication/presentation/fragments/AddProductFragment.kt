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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddProductBinding
import com.example.myapplication.presentation.activity.MainActivity
import com.example.myapplication.presentation.adapter.ImageAdapter
import com.example.myapplication.presentation.adapter.ProductAdapter
import java.security.Permission
import java.util.jar.Manifest

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

    private lateinit var adapter: ImageAdapter
    private lateinit var binding: FragmentAddProductBinding
    private val read_permission = 101
    val uriList = ArrayList<Uri>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_product, container, false)

        initRecycler()

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

        binding.addImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Images"), 1)
        }

        return binding.root
    }
    fun initRecycler(){
        binding.rvImages.layoutManager =
            LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        adapter = ImageAdapter(MainActivity(), uriList)
        binding.rvImages.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==1 && resultCode == Activity.RESULT_OK){
            if(data?.clipData !==null){
                val x= data.clipData!!.itemCount

                for(i in 0 until x){
                    uriList.add(data.clipData!!.getItemAt(i).uri)
                }

            }
            else if(data?.data !=null){
                val imageUrl= data.data!!
                uriList.add(imageUrl)
            }
            adapter.notifyDataSetChanged()
        }

    }


}