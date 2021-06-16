package com.liang.androidskilldemo.act;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * created by zhangliang on 2021/6/11
 */
public class ItemViewModel extends ViewModel {
    private final MutableLiveData<Item> selectedItem = new MutableLiveData<Item>();
    public void selectItem(Item item) {
        selectedItem.setValue(item);
    }
    public LiveData<Item> getSelectedItem() {
        return selectedItem;
    }

    public static class Item {
        public String content;
        public int age;
        public String desc;
    }
}
