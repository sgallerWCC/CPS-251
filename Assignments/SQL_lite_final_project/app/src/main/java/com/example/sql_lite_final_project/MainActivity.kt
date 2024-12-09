package com.example.sql_lite_final_project

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity
import com.example.sql_lite_final_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: ContactListAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun clearFields() {
        binding.nameInput.setText("")
        binding.contactInput.setText("")
        binding.nameInput.requestFocus()
    }

    private fun listenerSetup() {
        binding.add.setOnClickListener {v->
            val name = binding.nameInput.text.toString()
            val phone = binding.contactInput.text.toString()

            if (name != "" && phone != "") {
                val contact = Contact(name, phone)
                viewModel.insertContact(contact)
                clearFields()
            } else {
                Snackbar.make(v,"You must enter a name and phone number",Snackbar.LENGTH_LONG)
                    .show()
                clearFields()
            }
        }
        binding.find.setOnClickListener {v ->
            val name = binding.nameInput.text.toString()
            if(name != "") {
                viewModel.findContact(
                    binding.nameInput.text.toString()
                )
            } else{
                Snackbar.make(v,"You must enter search criteria in the name field",Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.ascend.setOnClickListener {
            viewModel.ascend()?.observe(this){contacts->
                contacts.let{
                    adapter?.setContactList(it)
                }
            }
        }

        binding.descend.setOnClickListener {
            viewModel.descend()?.observe(this) { contacts ->
                contacts.let {
                    adapter?.setContactList(it)
                }
            }
        }
    }

    private fun observerSetup() {
        viewModel.getAllContacts()?.observe(this) { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        }
        viewModel.getSearchResults().observe(this) { contacts ->
            contacts?.let {
                if (it.isNotEmpty()) {
                    adapter?.setContactList(it)
                }
            }
        }
    }



    private fun recyclerSetup() {
        adapter = ContactListAdapter(viewModel)
        binding.contactRecycler.layoutManager = LinearLayoutManager(this)
        binding.contactRecycler.adapter = adapter
    }
}