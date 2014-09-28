package org.dbflute.utflute.seasar.bean;

import javax.annotation.Resource;

import org.dbflute.utflute.seasar.dbflute.exbhv.FooBhv;

/**
 * @author jflute
 * @since 0.4.0 (2014/03/20 Thursday)
 */
public abstract class FooBaseFacade {

    @Resource
    private FooBhv fooBhv; // super's private field

    public FooBhv superBehaviorInstance() {
        return fooBhv;
    }
}
