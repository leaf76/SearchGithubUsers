package com.leaf76.githubusers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.leaf76.githubusers.R;
import com.leaf76.githubusers.databinding.UserItemBinding;
import com.leaf76.githubusers.databinding.UserItemTwooneBinding;
import com.leaf76.githubusers.databinding.UserItemTwotwoBinding;
import com.leaf76.githubusers.models.Item;

import java.util.List;
import java.util.Objects;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ONEONE = 0;
    private static final int TYPE_TWOONE = 1;
    private static final int TYPE_TWOTWO = 2;


    private List<Item> items;
    UserAdapter(List<Item> items){
        this.items = items;
    }

    // Recycle the view list item One x One
    class UserViewHolderOneOne extends RecyclerView.ViewHolder{
            private UserItemBinding binding;
        UserViewHolderOneOne(UserItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    // Recycle the view list item Two x One
    class UserViewHolderTwoOne extends RecyclerView.ViewHolder{
        private UserItemTwooneBinding binding;
        UserViewHolderTwoOne(UserItemTwooneBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    // Recycle the view list item Two x Two
    class UserViewHolderTwoTwo extends RecyclerView.ViewHolder{
        private UserItemTwotwoBinding binding;
        UserViewHolderTwoTwo(UserItemTwotwoBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 0:
                UserItemBinding binding = UserItemBinding.inflate(layoutInflater,parent,false);
                return new UserViewHolderOneOne(binding);
            case 1:
                UserItemTwooneBinding bindingTO = UserItemTwooneBinding.inflate(layoutInflater, parent,false);
                return new UserViewHolderTwoOne(bindingTO);
            case 2:
                UserItemTwotwoBinding bindingTT = UserItemTwotwoBinding.inflate(layoutInflater, parent, false);
                return new UserViewHolderTwoTwo(bindingTT);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);

        switch (holder.getItemViewType()){
            case 0:
                UserViewHolderOneOne uvhoo = (UserViewHolderOneOne) holder;
                Glide.with(uvhoo.itemView.getContext())
                        .load(item.getAvatar_url())
                        .into(uvhoo.binding.itemAvatar);
//        holder.binding.itemLink.setText(item.getHtml_url());
                uvhoo.binding.itemName.setText(item.getLogin());
                break;
            case 1:
                UserViewHolderTwoOne uvhto = (UserViewHolderTwoOne) holder;
                Glide.with(uvhto.itemView.getContext())
                        .load(item.getAvatar_url())
                        .into(uvhto.binding.itemAvatar);
                uvhto.binding.itemName.setText(item.getLogin());
                uvhto.binding.itemLink.setText(item.getHtml_url());

                break;
            case 2:
                UserViewHolderTwoTwo ubhtt = (UserViewHolderTwoTwo) holder;
                Glide.with(ubhtt.itemView.getContext())
                        .load(item.getAvatar_url())
                        .into(ubhtt.binding.itemAvatar);
                ubhtt.binding.itemName.setText(item.getLogin());
                ubhtt.binding.itemLink.setText(item.getHtml_url());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clearItems(){
        int size = this.items.size();
        this.items.clear();
        notifyItemRangeRemoved(0,size);
    }

    public void swapItems(List<Item> newItems){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new UserDiffCallback(this.items,newItems));
        this.items.clear();
        this.items.addAll(newItems);
        result.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemViewType(int position) {
        final int modeFour = position % 3;
        switch (modeFour){
            case 0:
                return TYPE_ONEONE;
            case 1:
                return  TYPE_TWOONE;
            case 2:
                return  TYPE_TWOTWO;
        }
        return TYPE_TWOTWO;
    }

    // User Different call back
    private class UserDiffCallback extends DiffUtil.Callback {
        private List<Item> mOldList;
        private List<Item> mNewList;

        UserDiffCallback(List<Item> oldList, List<Item> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mOldList != null ? mOldList.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return mNewList != null ? mNewList.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            int oldId = mOldList.get(oldItemPosition).getId();
            int newId = mNewList.get(newItemPosition).getId();
            return Objects.equals(oldId, newId);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Item oldRepo = mOldList.get(oldItemPosition);
            Item newRepo = mNewList.get(newItemPosition);
            return Objects.equals(oldRepo, newRepo);
        }
    }


}
