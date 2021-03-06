/*
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
package com.wrmsr.presto.util.collect;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.wrmsr.presto.util.Files;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class FileKv
    implements Kv<String, byte[]>
{
    private final File directory;

    public FileKv(File directory)
    {
        this.directory = directory;
    }

    @Override
    public byte[] get(String key)
    {
        try {
            return Files.readFileBytes(new File(directory, key).getPath());
        }
        catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void put(String key, byte[] value)
    {
        try {
            Files.writeFileBytes(new File(directory, key).getPath(), value);
        }
        catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void remove(String key)
    {
        if (!new File(directory, key).delete()) {
            throw new RuntimeException("delete failed");
        }
    }

    @Override
    public Iterator<String> iterator()
    {
        return ImmutableList.copyOf(directory.list()).iterator();
    }
}
