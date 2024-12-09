package com.example.sql_lite_final_project

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactRepository (application: Application){
    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>>?
    var sortedContacts: LiveData<List<Contact>>?

    init {
        val db: ContactRoomDatabase? = ContactRoomDatabase.getDatabase(application)
        contactDao = db?.ContactDao()
        allContacts = contactDao?.selectAll()
        sortedContacts = allContacts
    }

    fun insertContact(newContact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newContact)
        }
    }

    private fun asyncInsert(contact: Contact) {
        contactDao?.insertContact(contact)
    }

    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }
    private fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    fun findContact(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    fun ascend(): LiveData<List<Contact>>?{
        sortedContacts = contactDao?.ascend()
        return sortedContacts
    }

    fun descend(): LiveData<List<Contact>>?{
        sortedContacts = contactDao?.descend()
        return sortedContacts
    }

    //USES THE DEFERRED TO RETURN THE VALUES TO THE AWAIT OF THE FIND PRODUCT
    private fun asyncFind(name: String): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            //NOTICE THIS IS A RETURN HERE BECAUSE IT IS A SELECT QUERY IT RETURNS A VALUE
            return@async contactDao?.findContact(name)
        }
}