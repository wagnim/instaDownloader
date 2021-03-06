package com.tuanvn91.instagramdownloader.adaptor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.tuanvn91.instagramdownloader.tabs.DownloadFragment;
import com.tuanvn91.instagramdownloader.tabs.HistoryFragment;

import java.lang.ref.WeakReference;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {
//    public static int count = 0;

    private final SparseArray<WeakReference<Fragment>> instantiatedFragments = new SparseArray<>();


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                DownloadFragment downloadFragment = new DownloadFragment();
                downloadFragment.setRetainInstance(true);
//                count++;
                return downloadFragment;
            case 1:
//                count++;
                return HistoryFragment.newInstance();

            default:
                return new DownloadFragment();
        }
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Download";
        } else {
            return "History";
        }
    }

    @Override
    @NonNull
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        instantiatedFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Nullable
    public Fragment getFragment(final int position) {
        final WeakReference<Fragment> wr = instantiatedFragments.get(position);
        if (wr != null) {
            return wr.get();
        } else {
            return null;
        }
    }

}
