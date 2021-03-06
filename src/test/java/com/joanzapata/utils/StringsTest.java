/**
 * Copyright 2014 Joan Zapata
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
package com.joanzapata.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

    @Test
    public void format_nominal() {
        Assert.assertEquals("Hello John Doe!",
                Strings.format("Hello {firstname} {lastname}!")
                        .with("firstname", "John")
                        .with("lastname", "Doe")
                        .build());
    }

    @Test
    public void format_nominal_custom_prefix_suffix() {
        Assert.assertEquals("Hello John Doe!",
                Strings.format("Hello [firstname] [lastname]!", "[", "]")
                        .with("firstname", "John")
                        .with("lastname", "Doe")
                        .build());
    }

    @Test(expected = MissingKeyException.class)
    public void format_missingArg() {
        Assert.assertEquals("Hello John Doe!",
                Strings.format("Hello {firstname} {lastname}!")
                        .with("firstname", "John")
                        .build());
    }

    @Test(expected = KeyNotFoundException.class)
    public void format_extraArg() {
        Assert.assertEquals("Hello John Doe!",
                Strings.format("Hello {firstname} {lastname}!")
                        .with("firstname", "John")
                        .with("lastname", "John")
                        .with("extra", "Extra")
                        .build());
    }

    @Test
    public void format_missingArg_noStrict() {
        Assert.assertEquals("Hello John {lastname}!",
                Strings.format("Hello {firstname} {lastname}!").strictMode(false)
                        .with("firstname", "John")
                        .build());
    }

    @Test
    public void format_extraArg_noStrict() {
        Assert.assertEquals("Hello John Doe!",
                Strings.format("Hello {firstname} {lastname}!").strictMode(false)
                        .with("firstname", "John")
                        .with("lastname", "Doe")
                        .with("extra", "Extra")
                        .build());
    }

}
