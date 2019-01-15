package com.ranjan.materialapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ranjan.materialapp.R;
import com.ranjan.materialapp.data.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by BlueSapling on 1/15/19.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    List<User> users;
    Callback callback;

    public UsersAdapter(Callback callback) {
        this.callback = callback;
        users = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_info, parent, false);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, final int position) {
        holder.userFirstName.setText(users.get(position).firstName);
        holder.userLastName.setText(users.get(position).lastName);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                callback.deleteUser(users.get(position));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateUsers(List<User> userList) {
        users = userList;
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        TextView userFirstName, userLastName;

        UserHolder(@NonNull View itemView) {
            super(itemView);

            userFirstName = itemView.findViewById(R.id.list_first_name);
            userLastName = itemView.findViewById(R.id.list_last_name);
        }
    }

    public interface Callback {
        void deleteUser(User user);
    }
}
