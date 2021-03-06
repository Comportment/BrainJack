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

import java.io.*;

/**
 * Created by Comportment at 17:24 on 26/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class BrainJack {
    private final int amount;
    private final InputStreamReader is;
    private byte[] cells;
    private int pointer;

    public BrainJack(int amount) {
        is = new InputStreamReader(System.in);
        this.amount = amount;
        init();
    }

    public BrainJack() {
        is = new InputStreamReader(System.in);
        amount = 65535;
        init();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new BrainJackEngine().eval(new FileReader(new File("/home/comportment/Projects/BrainJack/src/main/resources/example.bf"))));
    }

    protected void init() {
        cells = new byte[amount];
        pointer = 0;
    }

    public String interpret(File input) throws IOException {
        if (!input.canRead()) return "";
        BufferedReader reader = new BufferedReader(new FileReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return this.interpret(builder.toString());
    }

    public String interpret(String input) {
        int brack = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case Characters.NEXT: {
                    pointer = (pointer == amount - 1) ? 0 : pointer + 1;
                    break;
                }
                case Characters.LAST: {
                    pointer = (pointer == 0) ? amount - 1 : pointer - 1;
                    break;
                }
                case Characters.PLUS: {
                    cells[pointer]++;
                    break;
                }
                case Characters.MINUS: {
                    cells[pointer]--;
                    break;
                }
                case Characters.OUT: {
                    builder.append((char) cells[pointer]);
                    break;
                }
                case Characters.INPUT: {
                    try {
                        cells[pointer] = (byte) is.read();
                    } catch (Exception e) {
                        cells[pointer] = 0;
                    }
                    break;
                }
                case Characters.LBRACK: {
                    if (cells[pointer] == 0) {
                        i++;
                        while (brack > 0 || input.charAt(i) != Characters.RBRACK) {
                            switch (input.charAt(i)) {
                                case Characters.LBRACK: {
                                    brack++;
                                    break;
                                }
                                case Characters.RBRACK: {
                                    brack--;
                                    break;
                                }
                            }
                            i++;
                        }
                    }
                    break;
                }
                case Characters.RBRACK: {
                    if (cells[pointer] != 0) {
                        i--;
                        while (brack > 0 || input.charAt(i) != Characters.LBRACK) {
                            switch (input.charAt(i)) {
                                case Characters.LBRACK: {
                                    brack--;
                                    break;
                                }
                                case Characters.RBRACK: {
                                    brack++;
                                    break;
                                }
                            }
                            i--;
                        }
                    }
                }
            }
        }
        return builder.toString();
    }
}