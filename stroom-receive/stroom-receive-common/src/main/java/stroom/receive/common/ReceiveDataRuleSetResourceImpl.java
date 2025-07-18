/*
 * Copyright 2017 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stroom.receive.common;

import stroom.event.logging.rs.api.AutoLogged;
import stroom.receive.rules.shared.HashedReceiveDataRules;
import stroom.receive.rules.shared.ReceiveDataRuleSetResource;
import stroom.receive.rules.shared.ReceiveDataRules;
import stroom.util.logging.LambdaLogger;
import stroom.util.logging.LambdaLoggerFactory;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

import java.util.Objects;

@AutoLogged
public class ReceiveDataRuleSetResourceImpl implements ReceiveDataRuleSetResource {

    private static final LambdaLogger LOGGER = LambdaLoggerFactory.getLogger(ReceiveDataRuleSetResourceImpl.class);

    private final Provider<ReceiveDataRuleSetService> ruleSetServiceProvider;

    @Inject
    ReceiveDataRuleSetResourceImpl(final Provider<ReceiveDataRuleSetService> ruleSetServiceProvider) {
        this.ruleSetServiceProvider = ruleSetServiceProvider;
    }

    @Override
    public ReceiveDataRules fetch() {
        try {
            return ruleSetServiceProvider.get().getReceiveDataRules();
        } catch (final Exception e) {
            LOGGER.error("Error fetching receive data rules", e);
            throw e;
        }
    }

    @Override
    public HashedReceiveDataRules fetchHashedRules() {
        return ruleSetServiceProvider.get().getHashedReceiveDataRules();
    }

    @Override
    public ReceiveDataRules update(final ReceiveDataRules doc) {
        Objects.requireNonNull(doc);
        return ruleSetServiceProvider.get().updateReceiveDataRules(doc);
    }
}
