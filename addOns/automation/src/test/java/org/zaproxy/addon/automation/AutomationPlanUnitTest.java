/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2023 The ZAP Development Team
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
package org.zaproxy.addon.automation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.parosproxy.paros.Constant;
import org.zaproxy.zap.utils.I18N;

/** Unit test for {@link AutomationPlan}. */
class AutomationPlanUnitTest {

    private ExtensionAutomation ext;

    @BeforeEach
    void setup() {
        Constant.messages = new I18N(Locale.ENGLISH);
        ext = mock(ExtensionAutomation.class);
    }

    @Test
    void shouldLoadPlanWithoutJobs(@TempDir Path dir) throws IOException {
        // Given
        var file = dir.resolve("plan.yaml");
        Files.writeString(
                file,
                "---\n"
                        + "env:\n"
                        + "  contexts:\n"
                        + "  - name: \"Example\"\n"
                        + "    urls:\n"
                        + "    - \"https://www.example.com/\"\n");
        // When
        AutomationPlan plan = assertDoesNotThrow(() -> new AutomationPlan(ext, file.toFile()));
        // Then
        assertThat(plan.getJobs(), is(empty()));
        AutomationProgress progress = plan.getProgress();
        assertThat(progress.getErrors(), is(empty()));
        assertThat(progress.getWarnings(), is(empty()));
    }
}
