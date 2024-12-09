package com.example.sql_lite_final_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sql_lite_final_project.databinding.CardLayoutBinding
class ContactListAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    private var contactList:List<Contact>? = null

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val contact = contactList?.get(listPosition)
        holder.binding.contactName.text = contact?.name
        holder.binding.contactNumber.text = contact?.phone
        holder.binding.deleteImage.setOnClickListener(){
            if(contact != null){
                viewModel.deleteContact(contact.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun setContactList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return contactList?.size ?: 0
    }

    class ViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root){
    }
}