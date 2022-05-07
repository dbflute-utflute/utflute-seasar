/*
 * Copyright 2014-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.dbflute.utflute.seasar;

import java.lang.reflect.Field;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.dbflute.utflute.seasar.s2container.SmartDeployDependencyChecker;
import org.dbflute.utflute.seasar.sastruts.ActionUrlPatternChecker;

/**
 * @author jflute
 * @since 0.1.0 (2011/07/24 Sunday)
 */
public abstract class ContainerTestCase extends SeasarTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** The (main) data source for database. (NotNull: after injection) */
    @Resource
    private DataSource _xdataSource;

    // ===================================================================================
    //                                                                         JDBC Helper
    //                                                                         ===========
    /** {@inheritDoc} */
    @Override
    protected DataSource getDataSource() { // user method
        return _xdataSource;
    }

    // ===================================================================================
    //                                                                  Dependency Checker
    //                                                                  ==================
    protected void checkDependencyToLogic() {
        doCheckDependencyTo("Logic", "Logic");
    }

    protected void checkDependencyToService() {
        doCheckDependencyTo("Service", "Service");
    }

    protected void checkDependencyToHelper() {
        doCheckDependencyTo("Helper", "Helper");
    }

    protected void checkDependencyToBehavior() {
        doCheckDependencyTo("Behavior", "Bhv");
    }

    protected void doCheckDependencyTo(String title, String suffix) {
        policeStoryOfJavaClassChase(createSmartDeployDependencyChecker(title, suffix));
    }

    protected SmartDeployDependencyChecker createSmartDeployDependencyChecker(String title, String suffix) {
        return new EmbeddedSmartDeployDependencyChecker(title, suffix);
    }

    protected class EmbeddedSmartDeployDependencyChecker extends SmartDeployDependencyChecker {

        public EmbeddedSmartDeployDependencyChecker(String title, String suffix) {
            super(title, suffix);
        }

        @Override
        protected void processTargetClass(Class<?> clazz, Field field, Class<?> injectedType) {
            final String injectedClassName = extractInjectedClassName(injectedType);
            log(clazz.getSimpleName() + "." + field.getName() + " depends on " + injectedClassName);
        }
    }

    // ===================================================================================
    //                                                                    SAStruts Checker
    //                                                                    ================
    protected void checkActionUrlPattern() {
        doCheckActionUrlPattern("Action");
    }

    protected void doCheckActionUrlPattern(String actionSuffix) {
        policeStoryOfJavaClassChase(createActionUrlPatternChecker(actionSuffix));
    }

    protected ActionUrlPatternChecker createActionUrlPatternChecker(String actionSuffix) {
        return new ActionUrlPatternChecker(actionSuffix);
    }
}
