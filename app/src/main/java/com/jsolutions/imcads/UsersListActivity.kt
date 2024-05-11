package com.jsolutions.imcads

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsolutions.imcads.data.UserListAdapter
import com.jsolutions.imcads.databinding.ActivityUsersListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUsersListBinding.inflate(layoutInflater)
    }
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this)
        userListAdapter = UserListAdapter()
        binding.recyclerViewUsers.adapter = userListAdapter

        val userDao = App.database.userDao()
        GlobalScope.launch(Dispatchers.IO) {
            val users = userDao.getAllUsers()
            launch(Dispatchers.Main) {
                userListAdapter.submitList(users)
            }
        }
    }
}
