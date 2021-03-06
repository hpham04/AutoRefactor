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
package org.autorefactor.refactoring.rules;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.autorefactor.AutoRefactorPlugin;
import org.autorefactor.refactoring.Release;
import org.autorefactor.ui.ApplyRefactoringsJob;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.autorefactor.cfg.test.TestUtils.*;
import static org.junit.Assert.*;

/**
 * Tests all refactoring rules at the same time. This test verifies that all the
 * refactoring rules work together and do not introduce problems.
 */
@RunWith(value = Parameterized.class)
public class AllRefactoringsTest {

    private final String sampleName;

    public AllRefactoringsTest(String testName) {
        this.sampleName = testName;
    }

    @Parameters(name = "{0}Refactoring")
    public static Collection<Object[]> data() {
        final File samplesDir = new File("src/test/java/org/autorefactor/rules/all/samples_in");
        final File[] sampleFiles = samplesDir.listFiles(new EndsWithFileFilter("Sample.java"));
        Arrays.sort(sampleFiles);

        final List<Object[]> output = new ArrayList<Object[]>(sampleFiles.length);
        for (File sampleFile : sampleFiles) {
            output.add(new Object[] { sampleFile.getName() });
        }
        return output;
    }

    @Test
    public void testRefactoring() throws Exception {
        AutoRefactorPlugin.turnDebugModeOn();
        try {
            testRefactoring0();
        } catch (RuntimeException e) {
            if ("Unexpected exception".equals(e.getMessage())) {
                throw (Exception) e.getCause();
            }
            throw e;
        }
    }

    private void testRefactoring0() throws Exception {
        final File samplesDir = new File("src/test/java/org/autorefactor/rules/all");
        final File sampleIn = new File(samplesDir, "samples_in/" + sampleName);
        assertTrue(sampleName + ": sample in file " + sampleIn + " should exist", sampleIn.exists());
        final File sampleOut = new File(samplesDir, "samples_out/" + sampleName);
        assertTrue(sampleName + ": sample out file " + sampleOut + " should exist", sampleOut.exists());

        final String sampleInSource = readAll(sampleIn);
        final String sampleOutSource = readAll(sampleOut);

        final IPackageFragment packageFragment = JavaCoreHelper.getPackageFragment();
        final ICompilationUnit cu = packageFragment.createCompilationUnit(
                sampleName, sampleInSource, true, null);
        cu.getBuffer().setContents(sampleInSource);
        cu.save(null, true);

        final IDocument doc = new Document(sampleInSource);
        new ApplyRefactoringsJob(null, null).applyRefactoring(
                doc, cu,
                Release.javaSE("1.5.0"), 4,
                new AggregateASTVisitor(AllRefactorings.getAllRefactorings()));

        final String actual = normalize(
                doc.get().replaceAll("samples_in", "samples_out"));
        final String expected = normalize(sampleOutSource);
        assertEquals(sampleName + ": wrong output;", expected, actual);
    }

    private String normalize(String s) {
        return s.replaceAll("\t", "    ")
                .replaceAll("(\r\n|\r|\n)", "\n")
                .trim();
    }
}
