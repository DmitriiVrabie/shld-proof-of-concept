package com.dvrabie.poc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.dvrabie.shieldoid.checkAppSignature
import com.dvrabie.shieldoid.isDebugMode
import com.dvrabie.shieldoid.isEmulator
import com.scottyab.rootbeer.RootBeer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val statusesCode = "STATUSES_CODE"
    private val adapter = StatusAdapter(mutableListOf())
    private val rootBeer by lazy { RootBeer(this) }

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
        rvStatus.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun setListeners() {
        btnDebugMode.setOnClickListener {
            adapter.addItem(getString(R.string.debug_mode_res, isDebugMode()))
        }
        btnEmulator.setOnClickListener {
            adapter.addItem(getString(R.string.emulator_mode_res, isEmulator()))
        }
        btnSignature.setOnClickListener { adapter.addItem(checkAppSignature()) }
        btnRoot.setOnClickListener {
            adapter.addItem(getString(R.string.root_access_res, rootBeer.isRooted))
        }
    }

}
