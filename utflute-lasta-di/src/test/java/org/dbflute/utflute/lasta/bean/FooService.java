package org.dbflute.utflute.lasta.bean;

import javax.transaction.TransactionManager;

import org.dbflute.lasta.di.core.annotation.Binding;

/**
 * @author jflute
 * @since 0.4.0 (2014/03/16 Sunday)
 */
public class FooService {

    @Binding
    protected TransactionManager transactionManager;
}
