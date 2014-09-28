package org.dbflute.utflute.seasar.bean;

import javax.transaction.TransactionManager;

import org.seasar.framework.container.annotation.tiger.Binding;

/**
 * @author jflute
 * @since 0.4.0 (2014/03/16 Sunday)
 */
public class FooService {

    @Binding
    protected TransactionManager transactionManager;
}
