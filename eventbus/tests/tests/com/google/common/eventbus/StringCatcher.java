/*
 * Original Guava code is copyright (C) 2015 The Guava Authors.
 * Modifications from Guava are copyright (C) 2015 DiffPlug.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.common.eventbus;

import java.util.List;

import javax.annotation.Nullable;

import junit.framework.Assert;

import com.google.common.collect.Lists;

/**
 * A simple EventSubscriber mock that records Strings.
 *
 * For testing fun, also includes a landmine method that EventBus tests are
 * required <em>not</em> to call ({@link #methodWithoutAnnotation(String)}).
 *
 * @author Cliff Biffle
 */
public class StringCatcher {
	private List<String> events = Lists.newArrayList();

	@Subscribe
	public void hereHaveAString(@Nullable String string) {
		events.add(string);
	}

	public void methodWithoutAnnotation(@Nullable String string) {
		Assert.fail("Event bus must not call methods without @Subscribe!");
	}

	public List<String> getEvents() {
		return events;
	}
}
