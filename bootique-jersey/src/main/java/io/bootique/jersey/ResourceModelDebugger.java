/*
 * Licensed to ObjectStyle LLC under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ObjectStyle LLC licenses
 * this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.bootique.jersey;

import javax.ws.rs.core.Configuration;

import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Debugs all container resources.
 * 
 * @since 0.12
 */
public class ResourceModelDebugger implements ModelProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceModelDebugger.class);

	@Override
	public ResourceModel processResourceModel(ResourceModel resourceModel, Configuration configuration) {

		if (LOGGER.isDebugEnabled()) {
			resourceModel.getRootResources().forEach(this::debugResource);
		}

		return resourceModel;
	}

	private void debugResource(Resource resource) {
		LOGGER.debug("Resource: " + resource.getPath());
		resource.getChildResources().forEach(this::debugResource);
	}

	@Override
	public ResourceModel processSubResource(ResourceModel subResourceModel, Configuration configuration) {
		return subResourceModel;
	}
}
