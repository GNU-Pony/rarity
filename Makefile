# rarity – An extensible tiling window manager
# 
# Copyright © 2013  Mattias Andrée (maandree@member.fsf.org)
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.


#Optional definitions:
#    NO_XINERAMA  --  Do not compile with Xinerama support


# settings
OPTIMISATION = -g

# PLATFORM DEPENDENT: the file extension for library files
LIB_EXT = .so

# the library name
LIB = rarity

# commands
PKG_CONFIG = pkg-config
JAVAC = javac
JAVAH = javah

# language versions
C_STD = gnu11
JAVA_SOURCE = 7
JAVA_TARGET = $(JAVA_SOURCE)

# cc flags in groups
C_WARNINGS = -Wall -Wextra -pedantic
C_OPTIMISATION = $(OPTIMISATION)

# cc flags in stages
CFLAGS = $(C_WARNINGS) $(C_OPTIMISATION) -std=$(C_STD) $(shell "$(PKG_CONFIG)" --cflags x11)
CPPFLAGS =
LDFLAGS = $(shell "$(PKG_CONFIG)" --libs x11)
ifdef NO_XINERAMA
  CPPFLAGS += -DNO_XINERAMA
else
  CFLAGS += $(shell "$(PKG_CONFIG)" --cflags xinerama)
  LDFLAGS += $(shell "$(PKG_CONFIG)" --libs xinerama)
endif

# cc flags for jni
JNI_INCLUDE = -I$(shell echo $${JAVA_HOME})/include
JNI_C_CFLAGS = $(JNI_INCLUDE) -fPIC
JNI_C_LDFLAGS = -shared

# javac flags
JAVA_VERSION = -source $(JAVA_SOURCE) -target $(JAVA_TARGET)
JAVA_WARNINGS = -Xlint
JAVA_OPTIMISATION = $(shell echo '$(OPTIMISATION)' | sed -e 's/[1-6]//g' -e 's/-O0//g')
JAVA_BOOTCLASSPATH =
JAVA_CLASSPATH = -classpath src
JAVA_SRCPATH = -s src
JAVA_BINPATH = -d bin
JAVA_PATHS = $(JAVA_BOOTCLASSPATH) $(JAVA_CLASSPATH) $(JAVA_SRCPATH) $(JAVA_BINPATH)
JAVA_FLAGS = $(JAVA_VERSION) $(JAVA_WARNINGS) $(JAVA_OPTIMISATION) $(JAVA_PATHS)

# javah flags
H_CLASSPATH = -classpath bin
H_PATHS = $(JAVA_BOOTCLASSPATH) $(H_CLASSPATH)
H_FLAGS = $(H_PATHS)

# c files
C_SRC = $(shell find src | grep '\.c$$')
C_OBJ = $(shell find src | grep '\.c$$' | sed -e 's/\.c$$/\.o/g' -e 's/^src\//obj\//g')

# java files
JAVA_SRC = $(shell find src | grep '\.java$$')
JAVA_CLASS = $(shell find src | grep '\.java$$' | sed -e 's/\.java$$/\.class/g' -e 's/^src\//bin\//g')

# h files
JNI_H = X11 Xinerama Rarity

# so files
LIB_PREFIX =
LIB_RARITY = $(LIB_PREFIX)$(LIB)$(LIB_EXT)


# compile
all: $(JAVA_CLASS) $(foreach H, $(JNI_H), src/rarity/$(H).h) $(C_OBJ)
# $(LIB_RARITY)

# generate .h
h: $(JNI_H)


# .o files
obj/%.o: src/%.c
	@mkdir -p "$$(echo "$@" | sed -e 's_\(.*\)/.*_\1_g')"
	$(CC) $(CFLAGS) $(CPPFLAGS) $(JNI_C_CFLAGS) "$<" -c -o "$@"

# .so file
$(LIB_JAVAGTK): $(C_OBJ)
	@if [ ! "$@" = "$$(echo "$@" | sed -e s_/__g)" ]; then     \
	     mkdir -p "$$(echo "$@" | sed -e 's_\(.*\)/.*_\1_')";  \
	 fi
	$(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS) $(JNI_C_CFLAGS) $(JNI_C_LDFLAGS) $^ -o "$@"

# .class files
bin/%.class: src/%.java
	@mkdir -p "bin"
	$(JAVAC) $(JAVA_FLAGS) "$<"

# .h files
src/%.h: bin/%.class
	@sum=$$(md5sum "$@" 2>/dev/null || echo); 									  \
	 echo $(JAVAH) $(H_FLAGS) -o "$@" "$$(echo "$<" | sed -e 's_^bin/__g' -e 's_\.class$$__g' | sed -e 's_/_\._g')";  \
	 $(JAVAH) $(H_FLAGS) -o "$@" "$$(echo "$<" | sed -e 's_^bin/__g' -e 's_\.class$$__g' | sed -e 's_/_\._g')";	  \
	 if [ "$$(grep -v '^#' "$@" | wc -l | cut -d ' ' -f 1)" = 5 ]; then						  \
	     rm "$@";													  \
	 elif [ ! "$$(md5sum "$@" 2>/dev/null || echo)" = "$$sum" ]; then						  \
	     echo -e '\e[01;32m$@ has been updated\e[00m';								  \
	 fi


# clean up
.PHONY: clean
clean:
	-@rm -r bin obj $(LIB_RARITY) $$(find src | egrep '/[A-Z].*\.h') 2>/dev/null

