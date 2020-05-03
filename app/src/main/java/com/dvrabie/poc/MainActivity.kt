package com.dvrabie.poc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dvrabie.shieldoid.isDebugMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val statusesCode = "STATUSES_CODE"
    private val adapter = StatusAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        setupRecyclerView()
        setupToolbar()
    }

    private fun setupToolbar() {
        supportActionBar?.setTitle(R.string.proof_of_concept)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_clear) {
            adapter.clear()
            return false
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(statusesCode, ArrayList(adapter.items))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        adapter.updateList(savedInstanceState.getStringArrayList(statusesCode) ?: mutableListOf())
    }

    private fun setupRecyclerView() {
        rvStatus.adapter = adapter
    }

    private fun setListeners() {
        btnDebugMode.setOnClickListener { checkDebugMode() }
    }

    private fun checkDebugMode() {
        adapter.addItem(getString(R.string.debug_mode, isDebugMode()))
    }
}
