/*
 * Copyright 2017 Comportment | comportment@diax.me
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

package me.diax.comportment.brainjack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Comportment at 01:14 on 28/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class BrainJackFactory implements ScriptEngineFactory {

    public static BrainJackFactory getInstance() {
        return new BrainJackFactory();
    }

    @Override
    public String getEngineName() {
        return "BrainJack";
    }

    @Override
    public String getEngineVersion() {
        return "1.0.7";
    }

    @Override
    public List<String> getExtensions() {
        return Arrays.asList("b", "bf");
    }

    @Override
    public List<String> getMimeTypes() {
        return Collections.singletonList("text/*");
    }

    @Override
    public List<String> getNames() {
        return Arrays.asList("bf", "brainjack", "brainfuck", "bj");
    }

    @Override
    public String getLanguageName() {
        return "Brainfuck";
    }

    @Override
    public String getLanguageVersion() {
        return "1.0.0";
    }

    @Override
    public Object getParameter(String key) {
        return null;
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
        return null;
    }

    @Override
    public String getOutputStatement(String toDisplay) {
        return null;
    }

    @Override
    public String getProgram(String... statements) {
        return String.join("\n", statements);
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new BrainJackEngine();
    }
}