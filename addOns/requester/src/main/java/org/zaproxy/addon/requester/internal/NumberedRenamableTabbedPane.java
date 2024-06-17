/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2016 The ZAP Development Team
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
package org.zaproxy.addon.requester.internal;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.commons.lang3.StringUtils;
import org.parosproxy.paros.Constant;
import org.parosproxy.paros.model.OptionsParam;
import org.zaproxy.addon.requester.ExtensionRequester;

public abstract class NumberedRenamableTabbedPane extends JTabbedPane {

    private static final long serialVersionUID = 1L;
    private Integer nextTabNumber = 1;
    private Component hiddenComponent = new JLabel();
    private static final Icon PLUS_ICON = ExtensionRequester.createIcon("fugue/plus.png");

    public NumberedRenamableTabbedPane() {
        super();
        this.addChangeListener(
                new ChangeListener() {

                    // This can be implemented better
                    private boolean adding = false;

                    @Override
                    public void stateChanged(ChangeEvent e) {
                        NumberedRenamableTabbedPane ntp =
                                (NumberedRenamableTabbedPane) e.getSource();
                        if (!adding && ntp.getSelectedIndex() == ntp.getTabCount() - 1) {
                            // Clicked on plus tab or changed to it
                            adding = true;
                            ntp.addDefaultTab();
                            adding = false;
                        }
                    }
                });

        this.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        if (evt.getClickCount() == 2) {
                            int index = indexAtLocation(evt.getX(), evt.getY());
                            if (index > -1) {
                                Component comp = getTabComponentAt(index);
                                if (comp != null) {
                                    String newName =
                                            JOptionPane.showInputDialog(
                                                    Constant.messages.getString(
                                                            "requester.tab.rename"),
                                                    comp.getName());
                                    if (!StringUtils.isEmpty(newName)) {
                                        comp.setName(newName);
                                    }
                                }
                            }
                        }
                    }
                });
        this.addTab("", PLUS_ICON, hiddenComponent);
    }

    private String nextTabName() {
        return String.valueOf(nextTabNumber++);
    }

    public abstract void addDefaultTab();

    public void addTab(Component pane) {
        String tabName = nextTabName();
        int index = this.getTabCount() - 1;
        this.insertTab(tabName, null, pane, null, index);
        this.setTabComponentAt(index, new CloseTabPanel(tabName, this));
        this.setSelectedIndex(index);
    }

    void unload() {
        processEditorPanels(ManualHttpRequestEditorPanel::unload);
    }

    private void processEditorPanels(Consumer<ManualHttpRequestEditorPanel> consumer) {
        int editorPanels = getTabCount() - 1;
        for (int i = 0; i < editorPanels; i++) {
            consumer.accept((ManualHttpRequestEditorPanel) getComponentAt(i));
        }
    }

    void optionsChanged(OptionsParam optionsParam) {
        processEditorPanels(panel -> panel.optionsChanged(optionsParam));
    }
}
