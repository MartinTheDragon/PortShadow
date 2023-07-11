# PortShadow

A tool currently under development to aid in porting open source software from one platform or version to another, while
the original version still continuously evolves.

**No features are implemented yet though.**

## When to use

It might not be clear at first when to use this tool and what for, as Git on its own already offers views of patches
and diffs, so here are some examples to give you an idea. Generally this software is meant for open source software
ports to different platforms where the old/original version is still being developed.

- Large, rapidly evolving projects on different platforms
- Ports that use a completely different API so that internal files differ greatly
- Forks that diverged so much to a point where merging changes no longer works

## Requirements

- Both the upstream and downstream repositories must use Git, but support for more VCS is planned

## Features

Features this software will include:

**NONE OF THE FOLLOWING FEATURES ARE IMPLEMENTED YET!**

- [ ] Keeping track of changes upstream
- [ ] Linking upstream files to downstream port files
- [ ] Marking downstream port files as outdated
- [ ] Showing what commits upstream require a change in what files downstream
- [ ] An easy way to merge changes in completely different files automatically or assisted
- [ ] Different modes for storing file link databases

   - [ ] Local file
   - [ ] Branch inside downstream Git repository
   - [ ] Server

- [ ] Web app
- [ ] Desktop app
- [ ] Showing random statistics

## License

PortShadow<br>
Copyright (C) 2023 MartinTheDragon

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program. If not, see <https://www.gnu.org/licenses/>.
