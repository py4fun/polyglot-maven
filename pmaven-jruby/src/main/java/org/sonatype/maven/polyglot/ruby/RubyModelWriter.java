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

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelWriter;
import org.apache.maven.model.io.ModelWriter;
import org.codehaus.plexus.component.annotations.Component;
import org.sonatype.maven.polyglot.io.ModelWriterSupport;

/**
 * Ruby model writer.
 * 
 * @author mkristian
 * 
 * @since 0.8
 */
@Component(role = ModelWriter.class, hint = "ruby")
public class RubyModelWriter extends ModelWriterSupport {

    final Templates xslt;

    public RubyModelWriter() throws TransformerConfigurationException {
        // create an instance of TransformerFactory
        final TransformerFactory transFact = TransformerFactory.newInstance();
        final Source xsltSource = new StreamSource(getClass().getResourceAsStream("pom2ruby.xsl"));

        this.xslt = transFact.newTemplates(xsltSource);
    }

    public void write(final Writer output, final Map<String, Object> options,
            final Model model) throws IOException {

        final DefaultModelWriter writer = new DefaultModelWriter();
        final StringWriter pom = new StringWriter();
        writer.write(pom, options, model);
        final Source xmlSource = new StreamSource(new StringReader(pom.toString()));
        final Result result = new StreamResult(output);

        try {
            this.xslt.newTransformer().transform(xmlSource, result);
        }
        catch (final TransformerException e) {
            throw new IOException("error producing the ruby code from xml", e);
        }
    }
}
