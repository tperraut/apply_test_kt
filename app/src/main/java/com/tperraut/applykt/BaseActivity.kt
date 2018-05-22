package com.tperraut.applykt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

class BaseActivity : AppCompatActivity(), BaseAdapter.Listener {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: BaseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        mRecyclerView = findViewById(R.id.item_list)
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
        mAdapter = BaseAdapter(this)
        mAdapter.mDataSet = initDataSet()
        mRecyclerView.adapter = mAdapter
    }

    private fun initDataSet(): ArrayList<Model> {
        val dataSet: ArrayList<Model> = ArrayList()
        dataSet.add(Model(R.string.item_title, R.string.item_description, R.drawable.geronimo))
        dataSet.add(Model(R.string.item_title, R.string.item_description, R.drawable.geronimo))
        dataSet.add(Model(R.string.item_title, R.string.item_description, R.drawable.geronimo))
        return dataSet
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item?.itemId) {
            R.id.add_item_bt -> {
                mAdapter.addItem(
                        R.string.item_title,
                        R.string.item_description,
                        R.drawable.geronimo
                )
                mRecyclerView.smoothScrollToPosition(0)
                true
            }
            R.id.remove_item_bt -> {
                mAdapter.removeItem()
                true
            }
            else -> super.onOptionsItemSelected(item)
    }

    override fun onShareRequested(model: Model) {
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(model.mTitleRes))
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_text)))
    }

    override fun onDetailsRequested(model: Model) {
        Toast.makeText(this, model.mTitleRes, Toast.LENGTH_SHORT).show()
    }

}
