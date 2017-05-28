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

import javax.script.*;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Comportment at 01:13 on 28/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class BrainJackEngine extends AbstractScriptEngine {

    @Override
    public Object eval(String script, ScriptContext context) throws ScriptException {
        return new BrainJack().interpret(script);
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        StringBuilder input = new StringBuilder();
        try {
            int read;
            while ((read = reader.read()) != -1) {
                input.append((char) read);
            }
        } catch (IOException ignored) {
        }
        return eval(input.toString(), context);
    }

    @Override
    public Bindings createBindings() {
        return null;
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return BrainJackFactory.getInstance();
    }
}