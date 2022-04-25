package com.example.shoppinglist.ui.shoppinglist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.adapters.ShoppingItemAdapter
import com.example.shoppinglist.data.datasource.local.models.ShoppingModel
import com.example.shoppinglist.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

enum class ProviderType {
    BASIC
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var shoppingFactory: ShoppingViewModelFactory

    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, shoppingFactory)[ShoppingViewModel::class.java]

        val adapter = ShoppingItemAdapter()

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        adapter.setOnItemDeleted {
            viewModel.deleteItem(it)
        }

        adapter.setOnItemChanged { viewModel.updateItem(it) }
        viewModel.getAllShoppingItems().observe(this, Observer {
            when(it) {
                is Resource.Success -> {
                    adapter.setItems(it.data!!)
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
            }
        })


        fab.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(shoppingModel: ShoppingModel) {
                        viewModel.insert(shoppingModel).observe(this@MainActivity, Observer {
                            when(it) {
                                is Resource.Success -> {
                                }
                                is Resource.Loading -> {
                                }
                                is Resource.Error -> {
                                }
                            }
                        })
                    }
                }).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.loginOut -> {
                FirebaseAuth.getInstance().signOut()
                onBackPressed()
                return true
            } else -> super.onOptionsItemSelected(item)
        }
        return true
    }

}
