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

import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * Created by Comportment at 17:24 on 26/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class BrainJack {

    byte[] cells;
    int pointer;

    public BrainJack() {
        cells = new byte[32];
        pointer = 0;
    }

    public void evalutate(String input) {
        StringBuilder output = new StringBuilder();
        CharBuffer.wrap(input).chars().mapToObj(ch -> (char)ch).forEach(c -> {
            switch (c) {
                case Characters.LAST:
                    if (pointer >= 1) pointer--;
                    break;
                case Characters.NEXT:
                    pointer++;
                    break;
                case Characters.PLUS:
                    cells[pointer]++;
                    break;
                case Characters.MINUS:
                    cells[pointer]--;
                    break;
                case Characters.OUT:
                    output.append(Character.toString((char) cells[pointer]));
            }
        });
        if (!output.toString().isEmpty()) System.out.println(output);
    }

    public static void main(String[] args) {
        new BrainJack().evalutate(
                "++++++++++++++++++++++++++++++++++++++++.>+++++++++++++++++++++++++++++++++.>+++++++++++++++++++++++++++++++++++++++++."
        );
    }
}