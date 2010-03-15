project { m =>
  m.artifactId = "pmaven"  
  m.build { b =>  
    b.defaultGoal = "install"    
    b.pluginManagement { p =>    
      p.plugin { p =>      
        p.artifactId = "maven-site-plugin"        
        p.extensions = false        
        p.groupId = "org.apache.maven.plugins"        
        p.inherited = true        
        p.version = "2.1"        
      }      
    }    
    b.plugin { p =>    
      p.artifactId = "maven-surefire-plugin"      
      p.configuration =      
      <configuration>      
        <redirectTestOutputToFile>true</redirectTestOutputToFile>        
        <forkMode>once</forkMode>        
        <argLine>-ea</argLine>        
        <failIfNoTests>false</failIfNoTests>        
        <workingDirectory>{"${project.build.directory}"}</workingDirectory>        
        <excludes>        
          <exclude>**/Abstract*.java</exclude>          
          <exclude>**/Test*.java</exclude>          
        </excludes>        
        <includes>        
          <include>**/*Test.java</include>          
        </includes>        
      </configuration>      
      p.extensions = false      
      p.groupId = "org.apache.maven.plugins"      
      p.inherited = true      
      p.version = "2.5"      
    }    
    b.plugin { p =>    
      p.artifactId = "maven-compiler-plugin"      
      p.configuration =      
      <configuration>      
        <source>1.5</source>        
        <target>1.5</target>        
      </configuration>      
      p.extensions = false      
      p.groupId = "org.apache.maven.plugins"      
      p.inherited = true      
      p.version = "2.0.2"      
    }    
    b.plugin { p =>    
      p.artifactId = "gmaven-plugin"      
      p.configuration =      
      <configuration>      
        <providerSelection>1.7</providerSelection>        
      </configuration>      
      p.execution { p =>      
        p.goals += "generateStubs"        
        p.goals += "compile"        
        p.goals += "generateTestStubs"        
        p.goals += "testCompile"        
        p.id = "default"        
        p.inherited = true        
        p.priority = 0        
      }      
      p.extensions = false      
      p.groupId = "org.codehaus.gmaven"      
      p.inherited = true      
      p.version = "1.2"      
    }    
    b.plugin { p =>    
      p.artifactId = "plexus-component-metadata"      
      p.execution { p =>      
        p.goals += "generate-metadata"        
        p.goals += "generate-test-metadata"        
        p.id = "default"        
        p.inherited = true        
        p.priority = 0        
      }      
      p.extensions = false      
      p.groupId = "org.codehaus.plexus"      
      p.inherited = true      
      p.version = "1.5.1"      
    }    
    b.plugin { p =>    
      p.artifactId = "maven-release-plugin"      
      p.configuration =      
      <configuration>      
        <useReleaseProfile>false</useReleaseProfile>        
        <goals>deploy</goals>        
        <arguments>-B -Prelease</arguments>        
        <autoVersionSubmodules>true</autoVersionSubmodules>        
      </configuration>      
      p.extensions = false      
      p.groupId = "org.apache.maven.plugins"      
      p.inherited = true      
      p.version = "2.0-beta-9"      
    }    
    b.plugin { p =>    
      p.artifactId = "maven-scm-plugin"      
      p.extensions = false      
      p.groupId = "org.apache.maven.plugins"      
      p.inherited = true      
      p.version = "1.2"      
    }    
  }  
  m.ciManagement { c =>  
    c.system = "Hudson"    
    c.url = "https://grid.sonatype.org/ci/job/Polyglot-Maven"    
  }  
  m.dependency { d =>  
    d.artifactId = "junit"    
    d.groupId = "junit"    
    d.optional = false    
    d.scope = "test"    
    d._type = "jar"    
    d.version = "4.7"    
  }  
  m.dependency { d =>  
    d.artifactId = "groovy"    
    d.groupId = "org.codehaus.groovy"    
    d.optional = false    
    d.scope = "test"    
    d._type = "jar"    
  }  
  m.dependencyManagement { d =>  
    d.dependency { d =>    
      d.artifactId = "apache-maven"      
      d.classifier = "bin"      
      d.groupId = "org.apache.maven"      
      d.optional = false      
      d._type = "zip"      
      d.version = "${mavenVersion}"      
    }    
    d.dependency { d =>    
      d.artifactId = "maven-model-builder"      
      d.groupId = "org.apache.maven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "${mavenVersion}"      
    }    
    d.dependency { d =>    
      d.artifactId = "maven-embedder"      
      d.groupId = "org.apache.maven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "${mavenVersion}"      
    }    
    d.dependency { d =>    
      d.artifactId = "maven-plugin-api"      
      d.groupId = "org.apache.maven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "${mavenVersion}"      
    }    
    d.dependency { d =>    
      d.artifactId = "groovy"      
      d.exclusion { e =>      
        e.artifactId = "jline"        
        e.groupId = "jline"        
      }      
      d.exclusion { e =>      
        e.artifactId = "junit"        
        e.groupId = "junit"        
      }      
      d.exclusion { e =>      
        e.artifactId = "ant"        
        e.groupId = "org.apache.ant"        
      }      
      d.exclusion { e =>      
        e.artifactId = "ant-launcher"        
        e.groupId = "org.apache.ant"        
      }      
      d.groupId = "org.codehaus.groovy"      
      d.optional = false      
      d._type = "jar"      
      d.version = "1.7.0"      
    }    
    d.dependency { d =>    
      d.artifactId = "guice"      
      d.groupId = "com.google.inject"      
      d.optional = false      
      d._type = "jar"      
      d.version = "2.0"      
    }    
    d.dependency { d =>    
      d.artifactId = "mvnsh-maven"      
      d.groupId = "org.sonatype.maven.shell"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.10"      
    }    
    d.dependency { d =>    
      d.artifactId = "gshell-core"      
      d.classifier = "tests"      
      d.groupId = "org.sonatype.gshell"      
      d.optional = false      
      d._type = "jar"      
      d.version = "2.5"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-common"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-cli"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-maven-plugin"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-groovy"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-yaml"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-jruby"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-commands"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-clojure"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
    d.dependency { d =>    
      d.artifactId = "pmaven-scala"      
      d.groupId = "org.sonatype.pmaven"      
      d.optional = false      
      d._type = "jar"      
      d.version = "0.8-SNAPSHOT"      
    }    
  }  
  m.developer { d =>  
    d.email = "jason@planet57.com"    
    d.id = "jdillon"    
    d.name = "Jason Dillon"    
    d.roles += "Build Master"    
    d.roles += "Developer"    
  }  
  m.distributionManagement { d =>  
    d.site { s =>    
      s.id = "${forgeSiteId}"      
      s.url = "${forgeSiteUrl}"      
    }    
  }  
  m.groupId = "org.sonatype.pmaven"  
  m.issueManagement { i =>  
    i.system = "JIRA"    
    i.url = "https://issues.sonatype.org/browse/PMAVEN"    
  }  
  m.license { l =>  
    l.distribution = "repo"    
    l.name = "The Apache Software License, Version 2.0"    
    l.url = "http://www.apache.org/licenses/LICENSE-2.0.txt"    
  }  
  m.mailingList { m =>  
    m.name = "Development"    
    m.post = "polyglot@maven.org"    
    m.subscribe = "polyglot-subscribe@maven.org"    
  }  
  m.modelEncoding = "UTF-8"  
  m.modelVersion = "4.0.0"  
  m.modules += "pmaven-common"  
  m.modules += "pmaven-maven-plugin"  
  m.modules += "pmaven-groovy"  
  m.modules += "pmaven-yaml"  
  m.modules += "pmaven-clojure"  
  m.modules += "pmaven-jruby"  
  m.modules += "pmaven-scala"  
  m.modules += "pmaven-cli"  
  m.modules += "pmaven-commands"  
  m.name = "Polyglot Maven"  
  m.packaging = "pom"  
  m.parent { p =>  
    p.artifactId = "forge-parent"    
    p.groupId = "org.sonatype.forge"    
    p.relativePath = "../pom.xml"    
    p.version = "5"    
  }  
  m.properties += ("forgeSiteId" -> "forge-sites")  
  m.properties += ("mavenVersion" -> "3.0-alpha-7")  
  m.properties += ("project.build.sourceEncoding" -> "UTF-8")  
  m.properties += ("forgeSiteUrl" -> "dav:http://repository.sonatype.org/content/sites/forge-sites/${project.artifactId}/${project.version}")  
  m.reporting { r =>  
    r.excludeDefaults = false    
    r.plugin { r =>    
      r.artifactId = "maven-javadoc-plugin"      
      r.configuration =      
      <configuration>      
        <configuration>        
          <source>1.5</source>          
          <encoding>{"${project.build.sourceEncoding}"}</encoding>          
        </configuration>        
      </configuration>      
      r.groupId = "org.apache.maven.plugins"      
      r.inherited = true      
      r.version = "2.6.1"      
    }    
    r.plugin { r =>    
      r.artifactId = "cobertura-maven-plugin"      
      r.groupId = "org.codehaus.mojo"      
      r.inherited = false      
      r.version = "2.3"      
    }    
  }  
  m.scm { s =>  
    s.connection = "scm:git:git://github.com/sonatype/polyglot-maven.git"    
    s.developerConnection = "scm:git:ssh://git@github.com/sonatype/polyglot-maven.git"    
    s.tag = "HEAD"    
    s.url = "http://github.com/sonatype/polyglot-maven"    
  }  
  m.url = "http://polyglot.sonatype.org/"  
  m.version = "0.8-SNAPSHOT"  
}
