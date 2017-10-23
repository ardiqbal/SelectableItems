package com.example.ardianza.selectableitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectableViewHolder.OnItemSelectedListener{

    RecyclerView recyclerView;
    SelectableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) this.findViewById(R.id.selection_list);
        recyclerView.setLayoutManager(layoutManager);
        List<ListItem> selectableItems = generateItems();
        adapter = new SelectableAdapter(this, selectableItems, true);
        recyclerView.setAdapter(adapter);

    }

    public List<ListItem> generateItems(){

        List<ListItem> selectableItems = new ArrayList<>();
        selectableItems.add(new ListItem("cem","karaca"));
        selectableItems.add(new ListItem("sezen","aksu"));
        selectableItems.add(new ListItem("baris","manco"));

        return selectableItems;
    }

    String selected = "";
    @Override
    public void onItemSelected(SelectableItem item) {
        List<ListItem> selectedItems = adapter.getSelectedItems();
        selected = "";
        for(ListItem selectedItem : selectedItems){
            selected = selected.concat(","+selectedItem.getHead());
        }
        Toast.makeText(this, "Selected item is "+ selected +", Totally  selectem item count is "+selectedItems.size(), Toast.LENGTH_LONG).show();
    }
}
