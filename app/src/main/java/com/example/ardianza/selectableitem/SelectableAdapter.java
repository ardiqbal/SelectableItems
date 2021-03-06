package com.example.ardianza.selectableitem;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ardianza on 08/09/17.
 */

public class SelectableAdapter extends RecyclerView.Adapter implements SelectableViewHolder.OnItemSelectedListener {

    private final List<SelectableItem> mValues;
    private boolean isMultiSelectionEnabled = false;
    SelectableViewHolder.OnItemSelectedListener listener;

    public SelectableAdapter(SelectableViewHolder.OnItemSelectedListener listener, List<ListItem> items, boolean isMultiSelectionEnabled) {
        this.listener = listener;
        this.isMultiSelectionEnabled = isMultiSelectionEnabled;

        mValues = new ArrayList<>();
        for (ListItem item : items) {
            mValues.add(new SelectableItem(item, false));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.checked_item, parent, false);
        return new SelectableViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SelectableViewHolder holder = (SelectableViewHolder) viewHolder;
        SelectableItem selectableItem = mValues.get(position);
        String name = selectableItem.getHead();
        holder.textView.setText(name);
        if (isMultiSelectionEnabled) {
            TypedValue value = new TypedValue();
            holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
            int checkMarkDrawableResId = value.resourceId;
            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        } else {
            TypedValue value = new TypedValue();
            holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
            int checkMarkDrawableResId = value.resourceId;
            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        }

        holder.mItem = selectableItem;
        holder.setChecked(holder.mItem.isSelected());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public List<ListItem> getSelectedItems() {
        List<ListItem> selectedItems = new ArrayList<>();
        for (SelectableItem item : mValues) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    @Override
    public int getItemViewType(int position) {
        if(isMultiSelectionEnabled){
            return SelectableViewHolder.MULTI_SELECTION;
        }
        else{
            return SelectableViewHolder.SINGLE_SELECTION;
        }
    }

    @Override
    public void onItemSelected(SelectableItem item) {
        Log.e("coba", String.valueOf(item.isSelected()));
        if (!isMultiSelectionEnabled) {
            for (SelectableItem selectableItem : mValues) {
                if (!selectableItem.equals(item) && selectableItem.isSelected()) {
                    selectableItem.setSelected(false);
                } else if (selectableItem.equals(item) && item.isSelected()) {
                    selectableItem.setSelected(true);
                }
            }
            notifyDataSetChanged();
        }
        listener.onItemSelected(item);
    }
}
