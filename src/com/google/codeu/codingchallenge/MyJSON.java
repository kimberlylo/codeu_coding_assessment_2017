// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.util.Collection;

final class MyJSON implements JSON {

	private String key;
	private String val;
	private String[] keyList = new String[2];
	private String[] valList = new String[2];
	private JSON innerJSON;
	private Collection<String> objNames;
	private Collection<String> stringNames;

	// case 1: {}
	public MyJSON() {
		key = null;
		val = null;
		innerJSON = null;
		objNames = null;
		stringNames = null;
	}

	// case 2: {"key":"val"}
	public MyJSON(String jKey, String jVal) {
		key = jKey;
		val = jVal;
		innerJSON = null;
		objNames = null;
		stringNames = null;
	}

	// {"key":"val", "key":"val"}
	public MyJSON(String[] jKey, String[] jVal) {
		keyList = jKey;
		valList = jVal;
		key = null;
		val = null;
		innerJSON = null;
		objNames = null;
		stringNames = null;
	}

	// case 3: {"key1":{"key2":"val2","key3":"val3"}}
	public MyJSON(String jKey, JSON jsonObj) {
		key = jKey;
		val = null;
		innerJSON = jsonObj;
		objNames = null;
		stringNames = null;
	}

	@Override
	public JSON getObject(String name) {
		// TODO: implement this
		// Get the value of the nested object with the given name. If there is
		// no nested object with that name, the method will return null.
		if (key != null && name.equals(key)) {
			return innerJSON;
		}
		return null;
	}

	@Override
	public JSON setObject(String name, JSON value) {
		// TODO: implement this
		// Set the value of the nested object with the given name. Any old value
		// should be overwritten. This method will always return a reference to
		// "this".
		if (key != null && name.equals(key)) {
			innerJSON = value;
		}
		return this;
	}

	@Override
	public String getString(String name) {
		// TODO: implement this
		// Get the string value within this object that has the given name. if
		// there is no string with the given name, the method will return null.

		if (key != null && name.equals(key)) {
			return val;
		}

		if (keyList[0] != null) {
			if (keyList[0].equals(name)) {
				return valList[0];
			} else if (keyList[1].equals(name)) {
				return valList[1];
			} else {
				return null;
			}
		}

		return null;
	}

	@Override
	public JSON setString(String name, String value) {
		// TODO: implement this
		// Set the string that should be stored under the given name. Any old
		// value
		// should be overwritten. This method will always return a reference to
		// "this".
		if (name.equals(key)) {
			if (keyList[0] != null) {
				if (keyList[0].equals(name)) {
					valList[0] = value;
				} else if (keyList[1].equals(name)) {
					valList[1] = value;
				}
			} else {
				val = value;
			}
		}
		return this;
	}

	@Override
	public void getObjects(Collection<String> names) {
		// TODO: implement this
		// Copy the names of all object values to the given collection.

		// case 1 || case 2
		if (key == null || val != null) {
			names = null;
		}

		// case 3
		else {
			objNames.add(key);
			names = objNames;
		}
	}

	@Override
	public void getStrings(Collection<String> names) {
		// TODO: implement this
		// Copy the names of all string values to the given collection.

		// case 1 || case 3
		if (key == null || innerJSON != null) {
			names = null;
		}

		// case 2
		else {
			stringNames.add(key);
			names = stringNames;
		}
	}
}