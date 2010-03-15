Current Issues
==============

* Needed rubygem get installed on a system level during build the artifact - 
  it does not go into the distribution or gets installed on run via distribution executable

* Current build install of rubygem uses the "latest" version which might break the build in future
  (the used rake-maven-plugin can not handle to install a specific version)

To see the effect of how things break just uninstall the 'builder' gem and run polygot-maven with the ruby pom

    java -jar ~/.m2/repository/org/jruby/jruby-complete/1.4.0/jruby-complete-1.4.0.jar -S gem uninstall builder
    unset JRUBY_HOME
    ./pmaven-*/bin/mvn -f pom.rb
