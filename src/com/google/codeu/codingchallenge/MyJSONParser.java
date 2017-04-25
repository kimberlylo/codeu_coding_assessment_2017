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

import java.io.IOException;

final class MyJSONParser implements JSONParser {

	// Given a string that should be a valid JSON-lite object encoded as a
	// string
	// return the parsed object. If for any reason the string is found to be
	// invalid, the method should throw an IOException.
	@Override
	public JSON parse(String in) throws IOException {
		// TODO: implement this

		JSON rObj;
		
		// {}
		if (!in.contains(":")) {
			MyJSON obj = new MyJSON();
			rObj = (JSON) obj;
		}

		// {"key1":{"key2":"val2","key3":"val3"}}
		else if (in.contains(",")) {

			// get key1
			int k1front = in.indexOf('"');
			int k1end = in.indexOf('"', k1front + 1);
			String key1 = in.substring(k1front + 1, k1end);

			// get key2
			int k2front = in.indexOf('"', k1end + 1);
			int k2end = in.indexOf('"', k2front + 1);
			String key2 = in.substring(k2front + 1, k2end);

			// get val2
			int v2front = in.indexOf('"', k2end + 1);
			int v2end = in.indexOf('"', v2front + 1);
			String val2 = in.substring(v2front + 1, v2end);

			// get key3
			int k3front = in.indexOf('"', v2end + 1);
			int k3end = in.indexOf('"', k3front + 1);
			String key3 = in.substring(k3front + 1, k3end);

			// get val3
			int v3front = in.indexOf('"', k3end + 1);
			int v3end = in.indexOf('"', v3front + 1);
			String val3 = in.substring(v3front + 1, v3end);

			String[] keys = { key2, key3 };
			String[] vals = { val2, val3 };

			MyJSON twoPairs = new MyJSON(keys, vals);

			MyJSON keyObj = new MyJSON(key1, twoPairs);
			rObj = (JSON) keyObj;

		}

		// {"key":"val"}
		else {

			// get key
			int kfront = in.indexOf('"');
			int kend = in.indexOf('"', kfront + 1);
			String key = in.substring(kfront + 1, kend);

			// get val
			int vfront = in.indexOf('"', kend + 1);
			int vend = in.indexOf('"', vfront + 1);
			String val = in.substring(vfront + 1, vend);

			MyJSON obj = new MyJSON(key, val);
			rObj = (JSON) obj;

		}
		
		return rObj;

	}
}
