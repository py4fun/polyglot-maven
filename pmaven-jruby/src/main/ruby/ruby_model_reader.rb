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

java_import 'org.apache.maven.model.Model'
java_import 'org.apache.maven.model.io.ModelReader'
java_import 'java.io.InputStream'
java_import 'java.io.Reader'
java_import 'java.io.File'
java_import 'java.util.Map'

class RubyModuleReader
  java_implements :ModelReader
  
  java_signature ['InputStream', 'Map<String,?>'] => :Model
  java_name :read
  def read_stream(stream, map)
    puts 'hello'
  end

  java_signature ['Reader', 'Map<String,?>'] => :Model
  java_name :read
  def read_reader(reader, map)
    puts 'hello'
  end

  java_signature ['File', 'Map<String,?>'] => :Model
  java_name :read
  def read_file(file, map)
    puts 'hello'
  end
  
end
