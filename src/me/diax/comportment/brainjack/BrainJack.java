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

import java.util.Scanner;

/**
 * Created by Comportment at 17:24 on 26/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class BrainJack {
    private final int cellSize;
    private byte[] cells;
    private int pointer;

    public BrainJack() {
        cellSize = 65535;
        cells = new byte[cellSize];
        pointer = 0;
    }

    public void interpret(String input) {
        int brack = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case Characters.NEXT: {
                    pointer = (pointer == cellSize - 1) ? 0 : pointer + 1;
                    break;
                }
                case Characters.LAST: {
                    pointer = (pointer == 0) ? cellSize - 1 : pointer - 1;
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
                        cells[pointer] = (byte) new Scanner(System.in).nextLine().charAt(0);
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
        System.out.println(builder);
    }

    public static void main(String[] args) {
        new BrainJack().interpret("++++++++++[>++++++<-]>.");
    }
}