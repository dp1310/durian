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
package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.features.CollectionFeature.SERIALIZABLE_INCLUDING_VIEWS;

import java.util.Set;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.testing.SerializableTester;

/**
 * A generic JUnit test which tests multiset-specific serialization. Can't be invoked directly;
 * please see {@link com.google.common.collect.testing.MultisetTestSuiteBuilder}.
 * 
 * @author Louis Wasserman
 */
@GwtCompatible // but no-op
public class MultisetSerializationTester<E> extends AbstractMultisetTester<E> {
	@CollectionFeature.Require(SERIALIZABLE_INCLUDING_VIEWS)
	public void testEntrySetSerialization() {
		Set<Multiset.Entry<E>> expected = getMultiset().entrySet();
		assertEquals(expected, SerializableTester.reserialize(expected));
	}

	@CollectionFeature.Require(SERIALIZABLE_INCLUDING_VIEWS)
	public void testElementSetSerialization() {
		Set<E> expected = getMultiset().elementSet();
		assertEquals(expected, SerializableTester.reserialize(expected));
	}
}
