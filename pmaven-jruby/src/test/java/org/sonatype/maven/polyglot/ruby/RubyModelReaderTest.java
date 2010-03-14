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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.IssueManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.Scm;
import org.apache.maven.model.io.DefaultModelReader;
import org.apache.maven.model.io.ModelWriter;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.junit.Test;

public class RubyModelReaderTest {
    @Test
    public void testModelCloning() throws Exception {
        getModel().clone();
    }

    @Test
    public void testModelReader() throws Exception {
        final Model model = getModel();
        assertNotNull(model);

        final Parent parent = model.getParent();
        assertEquals("org.sonatype.forge", parent.getGroupId());
        assertEquals("forge-parent", parent.getArtifactId());
        assertEquals("1.0", parent.getVersion());

        assertEquals("org.sonatype.pmaven", model.getGroupId());
        assertEquals("pmaven", model.getArtifactId());
        assertEquals("0.7-SNAPSHOT", model.getVersion());
        assertEquals("Polyglot Maven", model.getName());

        // Developers
        // final List<Developer> developers = model.getDevelopers();
        // final Developer dev0 = developers.get(0);
        // assertEquals("jvanzyl", dev0.getId());
        // assertEquals("Jason van Zyl", dev0.getName());

        // Contributors
        // final List<Contributor> contributors = model.getContributors();
        // final Contributor con0 = contributors.get(0);
        // assertEquals("Will Price", con0.getName());

        // DependencyManagement
        final List<Dependency> depManDependencies = model.getDependencyManagement()
                .getDependencies();
        assertEquals(16, depManDependencies.size());

        final Dependency dmd0 = depManDependencies.get(0);
        assertEquals("org.apache.maven", dmd0.getGroupId());
        assertEquals("apache-maven", dmd0.getArtifactId());
        // assertEquals("3.0", dmd0.getVersion());
        assertEquals("zip", dmd0.getType());
        assertEquals("bin", dmd0.getClassifier());

        final Dependency dmd1 = depManDependencies.get(1);
        assertEquals("org.apache.maven", dmd1.getGroupId());
        assertEquals("maven-model-builder", dmd1.getArtifactId());
        // assertEquals("3.0", dmd1.getVersion());

        // Dependencies
        final List<Dependency> dependencies = model.getDependencies();
        assertEquals(2, dependencies.size());

        final Dependency d0 = dependencies.get(0);
        assertEquals("junit", d0.getGroupId());
        assertEquals("junit", d0.getArtifactId());
        assertEquals("4.7", d0.getVersion());
        assertEquals("test", d0.getScope());

        final Dependency d1 = dependencies.get(1);
        assertEquals("org.codehaus.groovy", d1.getGroupId());
        assertEquals("groovy", d1.getArtifactId());
        assertEquals("4.0", d1.getVersion());
        assertEquals("test", d1.getScope());

        // Modules
        final List<String> modules = model.getModules();
        assertEquals(7, modules.size());
        assertEquals("pmaven-common", modules.get(0));
        assertEquals("pmaven-maven-plugin", modules.get(1));
        assertEquals("pmaven-groovy", modules.get(2));

        // Build
        final Build build = model.getBuild();
        final List<Plugin> plugins = build.getPlugins();
        assertEquals(6, plugins.size());
        final Plugin p0 = plugins.get(0);
        assertEquals("org.apache.maven.plugins", p0.getGroupId());
        assertEquals("maven-compiler-plugin", p0.getArtifactId());
        assertEquals("2.0.2", p0.getVersion());
        final Xpp3Dom configuration = (Xpp3Dom) p0.getConfiguration();
        assertNotNull(configuration);
        assertEquals(2, configuration.getChildCount());
        assertEquals("1.6", configuration.getChild("source").getValue());
        assertEquals("1.5", configuration.getChild("target").getValue());

        // DistributionManagement
        // final DistributionManagement distMan =
        // model.getDistributionManagement();
        // final Site site = distMan.getSite();
        // assertEquals("site", site.getId());
        // assertEquals("http://www.apache.org", site.getUrl());
        // final DeploymentRepository releases = distMan.getRepository();
        // assertEquals("releases", releases.getId());
        // assertEquals("releases", releases.getName());
        // assertEquals("http://maven.sonatype.org/releases",
        // releases.getUrl());
        // final DeploymentRepository snapshots =
        // distMan.getSnapshotRepository();
        // assertEquals("snapshots", snapshots.getId());
        // assertEquals("snapshots", snapshots.getName());
        // assertEquals("http://maven.sonatype.org/snapshots",
        // snapshots.getUrl());

        // SCM
        final Scm scm = model.getScm();
        assertEquals("scm:git:git://github.com/sonatype/polyglot-maven.git",
                     scm.getConnection());
        assertEquals("scm:git:ssh://git@github.com/sonatype/polyglot-maven.git",
                     scm.getDeveloperConnection());
        assertEquals("https://github.com/sonatype/polyglot-maven", scm.getUrl());

        // IssueManagement
        final IssueManagement issueManagement = model.getIssueManagement();
        assertEquals("JIRA", issueManagement.getSystem());
        assertEquals("https://issues.sonatype.org/browse/PMAVEN",
                     issueManagement.getUrl());

        // CiManagement
        // final CiManagement ciManagement = model.getCiManagement();
        // assertEquals("hudson", ciManagement.getSystem());
        // assertEquals("http://grid.sonatype.org/ci", ciManagement.getUrl());

        // Profiles
    }

    @Test
    public void testModelWriter() throws Exception {
        final StringWriter sw = new StringWriter();
        final ModelWriter writer = new RubyModelWriter();
        final Model model = getModel();
        final Properties p = new Properties();
        p.setProperty("FOO", "BAR");
        model.setProperties(p);
        writer.write(sw, null, model);
        System.out.println(sw.toString());
    }

    protected Model getModel() throws Exception {
        final RubyModelReader modelReader = new RubyModelReader();
        modelReader.xmlReader = new DefaultModelReader();
        final URL url = getClass().getResource("test.rb");
        assertNotNull(url);
        final InputStream reader = url.openStream();
        return modelReader.read(reader, null);
    }
}
