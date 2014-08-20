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
package org.autorefactor.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * This is the Eclipse handler for launching a wizard allowing to select
 * automated refactorings to apply. This is invoked from the Eclipse UI.
 *
 * @see <a href="http://www.vogella.com/tutorials/EclipseWizards/article.html"
 *      >Creating Eclipse Wizards - Tutorial </a>
 */
public class ChooseRefactoringsWizardHandler extends AbstractHandler {

    /** {@inheritDoc} */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final Shell shell = HandlerUtil.getActiveShell(event);
        // retrieve the targeted java element before the menu item is disposed by the framework
        final IJavaElement javaElement = AutoRefactorHandler.getSelectedJavaElement(event);
        final WizardDialog dialog = new WizardDialog(shell, new ChooseRefactoringsWizard(javaElement));
        dialog.open();
        return null;
    }

}