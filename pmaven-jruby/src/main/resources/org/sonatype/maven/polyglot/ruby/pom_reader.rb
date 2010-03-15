#
# Copyright (C) 2009 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

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
