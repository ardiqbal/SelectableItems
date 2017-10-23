package com.example.ardianza.selectableitem;

/**
 * Created by ardianza on 08/09/17.
 */

public class SelectableItem extends ListItem {

    private boolean isSelected = false;

    public SelectableItem(ListItem item, boolean isSelected) {
        super(item.getHead(), item.getDesc());
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
