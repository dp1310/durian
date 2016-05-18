/*
 * Original Guava code is copyright (C) 2015 The Guava Authors.
 * Modifications from Guava are copyright (C) 2016 DiffPlug.
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
package com.diffplug.common.collect;

import junit.framework.TestCase;

import com.diffplug.common.annotations.GwtCompatible;

/**
 * Tests {@link ImmutableTable}
 *
 * @author Gregory Kick
 */
@GwtCompatible
public abstract class AbstractImmutableTableTest extends TestCase {

	abstract Iterable<ImmutableTable<Character, Integer, String>> getTestInstances();

	@SuppressWarnings("deprecation")
	public final void testClear() {
		for (ImmutableTable<Character, Integer, String> testInstance : getTestInstances()) {
			try {
				testInstance.clear();
				fail();
			} catch (UnsupportedOperationException e) {
				// success
			}
		}
	}

	@SuppressWarnings("deprecation")
	public final void testPut() {
		for (ImmutableTable<Character, Integer, String> testInstance : getTestInstances()) {
			try {
				testInstance.put('a', 1, "blah");
				fail();
			} catch (UnsupportedOperationException e) {
				// success
			}
		}
	}

	@SuppressWarnings("deprecation")
	public final void testPutAll() {
		for (ImmutableTable<Character, Integer, String> testInstance : getTestInstances()) {
			try {
				testInstance.putAll(ImmutableTable.of('a', 1, "blah"));
				fail();
			} catch (UnsupportedOperationException e) {
				// success
			}
		}
	}

	@SuppressWarnings("deprecation")
	public final void testRemove() {
		for (ImmutableTable<Character, Integer, String> testInstance : getTestInstances()) {
			try {
				testInstance.remove('a', 1);
				fail();
			} catch (UnsupportedOperationException e) {
				// success
			}
		}
	}

	public final void testConsistentToString() {
		for (ImmutableTable<Character, Integer, String> testInstance : getTestInstances()) {
			assertEquals(testInstance.rowMap().toString(), testInstance.toString());
		}
	}

	public final void testConsistentHashCode() {
		for (ImmutableTable<Character, Integer, String> testInstance : getTestInstances()) {
			assertEquals(testInstance.cellSet().hashCode(), testInstance.hashCode());
		}
	}
}