package com.liang.androidskilldemo.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.liang.androidskilldemo.R;

public class FragmentCommunicateActivity extends AppCompatActivity {

    private ItemViewModel viewModel;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_communicate);
        tvTitle = findViewById(R.id.tvTitle);

        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(ItemViewModel.class);
        viewModel.getSelectedItem().observe(this, new Observer<ItemViewModel.Item>() {
            @Override
            public void onChanged(ItemViewModel.Item item) {
                tvTitle.setText(item.content);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, ItemFragment.newInstance(1))
                .commit();
    }
}