package org.geeklub.smartlib4admin;

import com.squareup.otto.Bus;

/**
 * Created by Vass on 2014/12/15.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    private BusProvider(){

    }
}
