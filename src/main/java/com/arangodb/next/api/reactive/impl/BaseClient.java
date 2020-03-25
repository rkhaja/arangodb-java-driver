/*
 * DISCLAIMER
 *
 * Copyright 2016 ArangoDB GmbH, Cologne, Germany
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
 *
 * Copyright holder is ArangoDB GmbH, Cologne, Germany
 */

package com.arangodb.next.api.reactive.impl;


import com.arangodb.next.api.reactive.ConversationManager;
import com.arangodb.next.communication.ArangoCommunication;
import com.arangodb.next.communication.CommunicationConfig;
import com.arangodb.next.entity.serde.ArangoSerde;

/**
 * @author Michele Rastelli
 */
public abstract class BaseClient {

    private final ArangoCommunication communication;
    private final ArangoSerde serde;
    private final ConversationManager conversationManager;

    protected BaseClient(final BaseClient other) {
        communication = other.communication;
        serde = other.serde;
        conversationManager = other.conversationManager;
    }

    protected BaseClient(final CommunicationConfig config) {
        communication = ArangoCommunication.create(config).block();
        serde = ArangoSerde.of(config.getContentType());
        conversationManager = new ConversationManagerImpl(communication);
    }

    public final ConversationManager getConversationManager() {
        return conversationManager;
    }

    protected final ArangoCommunication getCommunication() {
        return communication;
    }

    protected final ArangoSerde getSerde() {
        return serde;
    }

}
