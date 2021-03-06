/*
 * AutoRefactor - Eclipse plugin to automatically refactor Java code bases.
 *
 * Copyright (C) 2013 Jean-Noël Rouvignac - initial API and implementation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program under LICENSE-GNUGPL.  If not, see
 * <http://www.gnu.org/licenses/>.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution under LICENSE-ECLIPSE, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.autorefactor.samples_in;

public class StringSample {

    public String replaceNewString() {
        return new String("");
    }

    public void replaceStringToString(String s) {
        String s1 = s.toString();
        String s2 = "".toString();
        String s3 = getS().toString();
    }

    public void replaceToStringCallInStringConcat() {
        String s1 = "" + Boolean.TRUE.toString() + Boolean.FALSE.toString();
        String s2 = Boolean.TRUE.toString() + "" + Boolean.FALSE.toString();
    }

    public String doNotReplaceToStringCallOutsideStringConcat() {
        return Boolean.TRUE.toString();
    }

    public String doNotReplaceTwoConsecutiveToStringCalls() {
        return Boolean.TRUE.toString() + Boolean.FALSE.toString();
    }

    public void removeUselessToString() {
        byte b = 42;
        short s = 42;
        String s1 = Boolean.toString(true) + " foo";
        String s2 = Character.toString('c') + " foo";
        String s3 = Byte.toString(b) + " foo";
        String s4 = Short.toString(s) + " foo";
        String s5 = Integer.toString(42) + " foo";
        String s6 = Long.toString(42l) + " foo";
        String s7 = Float.toString(42.42f) + " foo";
        String s8 = Double.toString(42.42) + " foo";

        String s11 = String.valueOf(true) + " foo";
        String s12 = String.valueOf('c') + " foo";
        String s13 = String.valueOf(b) + " foo";
        String s14 = String.valueOf(s) + " foo";
        String s15 = String.valueOf(42) + " foo";
        String s16 = String.valueOf(42l) + " foo";
        String s17 = String.valueOf(42.42f) + " foo";
        String s18 = String.valueOf(42.42) + " foo";
    }

    // TODO JNR which operand must be removed here?
    // String s = "" + Integer.toString(42);

    private static String getS() {
        return null;
    }
}
