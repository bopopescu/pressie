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
package com.wrmsr.presto.launcher.passthrough;

import com.wrmsr.presto.launcher.LauncherModule;
import io.airlift.airline.Cli;

public class PassthroughModule
        extends LauncherModule
{
    @Override
    public void configureCli(Cli.CliBuilder<Runnable> builder)
    {
        builder.withCommands(
                CliCommand.class,
                H2Command.class,
                HdfsCommand.class,
                HiveCommand.class,
                JarSyncCommand.class,
                JythonCommand.class,
                NashornCommand.class);
    }
}
