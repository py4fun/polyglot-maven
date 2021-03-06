/*
 * Copyright (C) 2009 the original author or authors.
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

package org.sonatype.maven.polyglot.ruby;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.ModelReader;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.util.IOUtil;
import org.jruby.embed.ScriptingContainer;
import org.sonatype.maven.polyglot.io.ModelReaderSupport;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import static org.jruby.embed.LocalContextScope.*;
import static org.jruby.embed.LocalVariableBehavior.*;

/**
 * Ruby model reader.
 *
 * @author mkristian
 * @since 0.8
 */
@Component(role = ModelReader.class, hint = "ruby")
public class RubyModelReader
    extends ModelReaderSupport
{
    @Requirement(hint = "default")
    private ModelReader xmlReader;

    private ScriptingContainer scriptingContainer;

    private Object pomReader;

    public RubyModelReader() {
        this.scriptingContainer = new ScriptingContainer(SINGLETON, PERSISTENT);
        this.pomReader = this.scriptingContainer.runScriptlet(getClass().getResourceAsStream("pom_reader.rb"), "pom_reader.rb");
    }

    public Model read(final Reader input, final Map<String, ?> options) throws IOException {
        assert input != null;

        String pomSource = IOUtil.toString(input);
        String pomXml = scriptingContainer.callMethod(this.pomReader, "read", pomSource, String.class);

        return xmlReader.read(new StringReader(pomXml), options);
    }
}
