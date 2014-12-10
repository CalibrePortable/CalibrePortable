package org.geeklub.smartlib4admin.overdue;

import android.support.v7.widget.RecyclerView;

import org.geeklub.smartlib4admin.module.base.BasePageListFragment;

/**
 * Created by Vass on 2014/12/10.
 */
public class OverDueFragment extends BasePageListFragment {
    @Override
    protected RecyclerView.Adapter newAdapter() {
        return null;
    }

    @Override
    protected void executeRequest(int page) {

    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }
}
