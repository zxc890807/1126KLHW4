package com.example.hw6

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val transData = ArrayList<Data>()
    private val cubeeData = ArrayList<Data>()


    data class Data(
        val photo:Int,
        val name:String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transPhotoIdArray = resources.obtainTypedArray(R.array.transPhotoIdArray)
        val transNameArray=resources.getStringArray(R.array.transNameArray)
        val cubeePhotoIdArray=resources.obtainTypedArray(R.array.cubeePhotoIdArray)
        val cubeeNameArray=resources.getStringArray(R.array.cubeeNameArray)

        val spinner:Spinner=findViewById(R.id.spinner)
        val listView:ListView=findViewById(R.id.listView)
        val gridView:GridView=findViewById(R.id.GridView)


        for(i in 0 until transPhotoIdArray.length())
            transData.add(Data(transPhotoIdArray.getResourceId(i,0 ),transNameArray[i]))
        transPhotoIdArray.recycle()

        spinner.adapter=MyAdapter(transData,R.layout.trans_list)


        for(i in 0 until cubeePhotoIdArray.length())
            cubeeData.add(Data(cubeePhotoIdArray.getResourceId(i,0),cubeeNameArray[i]))
        cubeePhotoIdArray.recycle()

        gridView.numColumns=3
        gridView.adapter=MyAdapter(cubeeData,R.layout.cubee_list)

        listView.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1
            , arrayListOf("訊息1","訊息2","訊息3","訊息4","訊息5","訊息6"))

    }
}