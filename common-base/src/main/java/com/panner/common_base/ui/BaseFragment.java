package com.panner.common_base.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 基础通用的fragment
 *
 * @author Panner
 * @version 2019-04-07-22:39
 */
public class BaseFragment extends Fragment {
    private final String TAG = getFragment().getClass().getSimpleName();
    protected LayoutInflater mInflater;
    private View mContentView;
    private ViewGroup mContainer;

    public Fragment getFragment() {
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        this.mContainer = container;
        onCreateView(savedInstanceState);
        if (mContentView == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        return mContentView;
    }

    protected void onCreateView(Bundle savedInstanceState) {

    }

    public void setContentView(int layoutById) {
        setContentView(mInflater.inflate(layoutById, mContainer, false));
    }

    public void setContentView(View view) {
        mContentView = view;
    }

    public View getContentView() {
        return mContentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContainer = null;
        mContentView = null;
        mInflater = null;
    }
}
