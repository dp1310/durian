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
package com.google.common.collect;

import java.lang.ref.WeakReference;

import junit.framework.TestCase;

import com.google.common.base.Function;
import com.google.common.testing.GcFinalization;
import com.google.common.testing.NullPointerTester;

/**
 * Unit test for {@link Interners}.
 *
 * @author Kevin Bourrillion
 */
public class InternersTest extends TestCase {

	public void testStrong_simplistic() {
		String canonical = "a";
		String not = new String("a");

		Interner<String> pool = Interners.newStrongInterner();
		assertSame(canonical, pool.intern(canonical));
		assertSame(canonical, pool.intern(not));
	}

	public void testStrong_null() {
		Interner<String> pool = Interners.newStrongInterner();
		try {
			pool.intern(null);
			fail();
		} catch (NullPointerException ok) {}
	}

	public void testWeak_simplistic() {
		String canonical = "a";
		String not = new String("a");

		Interner<String> pool = Interners.newWeakInterner();
		assertSame(canonical, pool.intern(canonical));
		assertSame(canonical, pool.intern(not));
	}

	public void testWeak_null() {
		Interner<String> pool = Interners.newWeakInterner();
		try {
			pool.intern(null);
			fail();
		} catch (NullPointerException ok) {}
	}

	public void testWeak_afterGC() throws InterruptedException {
		Integer canonical = new Integer(5);
		Integer not = new Integer(5);

		Interner<Integer> pool = Interners.newWeakInterner();
		assertSame(canonical, pool.intern(canonical));

		WeakReference<Integer> signal = new WeakReference<Integer>(canonical);
		canonical = null; // Hint to the JIT that canonical is unreachable

		GcFinalization.awaitClear(signal);
		assertSame(not, pool.intern(not));
	}

	public void testAsFunction_simplistic() {
		String canonical = "a";
		String not = new String("a");

		Function<String, String> internerFunction = Interners.asFunction(Interners.<String> newStrongInterner());

		assertSame(canonical, internerFunction.apply(canonical));
		assertSame(canonical, internerFunction.apply(not));
	}

	public void testNullPointerExceptions() {
		new NullPointerTester().testAllPublicStaticMethods(Interners.class);
	}
}
