package it.jdark.android.example.recycleview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jDark on 08/11/15.
 */
public class Item {
    private String name;
    private String surName;
    private boolean status;

    public Item(String name, String surName, boolean status) {
        this.name = name;
        this.status = status;
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static List<Item> createItemList (int numItems) {
        List<Item> items = new ArrayList<Item>();

        for (int i=1; i<= numItems; i++) {
            items.add(new Item("Name "+i, "Sur Name "+i, i%2==0));
        }
        return items;
    }
}
