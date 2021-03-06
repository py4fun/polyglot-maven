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

# read the pom
file = ARGV[0] or raise "no file specified"
src = File.read(file)

# construct a builder
builder = Builder::XmlMarkup.new(:target => STDOUT, :indent => 2)

# define a _module method since module is a keyword
def builder._module(*args, &block); self.module(*args, &block); end

# execute the pom against builder
builder.instance_eval(src)
