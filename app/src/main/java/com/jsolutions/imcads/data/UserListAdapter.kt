package com.jsolutions.imcads.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jsolutions.imcads.R
import com.jsolutions.imcads.data.models.User

class UserListAdapter : ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int,
    ) {
        val user = getItem(position)
        holder.bind(user)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewHeight: TextView = itemView.findViewById(R.id.textViewHeight)
        private val textViewWeight: TextView = itemView.findViewById(R.id.textViewWeight)
        private val textUserId: TextView = itemView.findViewById(R.id.textUserId)

        fun bind(user: User) {
            textUserId.text = "User: ${user.id}"
            textViewHeight.text = "Altura: ${user.height} m"
            textViewWeight.text = "Peso: ${user.weight} kg"
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User,
        ): Boolean {
            return oldItem == newItem
        }
    }
}
