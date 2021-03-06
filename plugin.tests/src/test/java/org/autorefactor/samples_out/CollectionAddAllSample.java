/*
 * AutoRefactor - Eclipse plugin to automatically refactor Java code bases.
 *
 * Copyright (C) 2014 Jean-Noël Rouvignac - initial API and implementation
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
package org.autorefactor.samples_out;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionAddAllSample {

    public void replaceNewNoArgsAssignmentThenAddAll(List<String> col, List<String> output) {
        output = new ArrayList<String>(col);
    }

    public List<String> replaceNewNoArgsThenAddAll(List<String> col) {
        final List<String> output = new ArrayList<String>(col);
        return output;
    }

    public List<String> replaceNew0ArgThenAddAll(List<String> col) {
        final List<String> output = new ArrayList<String>(col);
        return output;
    }

    public List<String> replaceNew1ArgThenAddAll(List<String> col) {
        final List<String> output = new ArrayList<String>(col);
        return output;
    }

    public List<String> replaceNewCollectionSizeThenAddAll(List<String> col) {
        final List<String> output = new ArrayList<String>(col);
        return output;
    }

    public void replaceForLoop(List<String> col, List<String> output) {
        output.addAll(col);
    }

    public void replaceForEach(Collection<String> col, List<String> output) {
        output.addAll(col);
    }
}
