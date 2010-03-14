require 'rubygems'
require 'builder'

class PomReader

  def read(src)
    # buffer to hold output
    result = ""

    # construct a builder
    builder = Builder::XmlMarkup.new(:target => result, :indent => 2)

    # define a _module method since module is a keyword
    def builder._module(*args, &block); self.module(*args, &block); end

    # define a _system method otherwise a system call is made on evaluation
    def builder._system(*args, &block); self.system(*args, &block); end

    # execute the pom against builder
    builder.instance_eval(src)

    result
  end
end

PomReader.new
