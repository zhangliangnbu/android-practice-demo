package com.liang.androidskilldemo.act;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.liang.androidskilldemo.R;

import org.jetbrains.annotations.NotNull;

public class FragmentCommunicateActivity extends AppCompatActivity {

    private ItemViewModel viewModel;
    private TextView tvTitle;
    private TextView tvTitleByApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_communicate);
        tvTitle = findViewById(R.id.tvTitle);

        // by view model
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

        // by api
        tvTitleByApi = findViewById(R.id.tvTitleByAPI);
        getSupportFragmentManager().setFragmentResultListener("msgKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                String results = result.getString("bundleKey");
                tvTitleByApi.setText(results);
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container2, new SampleEFragment())
                .commit();

    }
}