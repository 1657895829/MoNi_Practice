package com.example.moni.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.moni.R;

/**
 * Created   by    Dewey
 * 清除缓存
 */

public class ClearCache_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clear_fragment, container, false);
        //Toast.makeText(getActivity(),"缓存已清理",Toast.LENGTH_SHORT).show();

        return view;
    }
}
