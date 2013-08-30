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
#    TESTING      --  Compile for testing


# extension information
EXTENSION = rarity.nightmare

# settings
OPTIMISATION = -g
JAR_COMPRESS = 0

# install settings
RARITY_PKGNAME = rarity
PREFIX =
EXTDIR = /opt
EXTPATH = $(PREFIX)$(EXTDIR)/$(RARITY_PKGNAME)
PATH = $(DESTDIR)$(EXTPATH)

# PLATFORM DEPENDENT: the file extension for library files
LIB_EXT = .so

# the library file without directory
LIBFILE = $(LIB_PREFIX)$(EXTENSION)$(LIB_EXT)

# commands
PKG_CONFIG = pkg-config
JAVAC = javac
JAVAH = javah
JAR = jar
JPP = jpp

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
JAVA_CLASSPATH = -classpath .:../bin
JAVA_SRCPATH = -s .
JAVA_BINPATH = -d ../../bin
JAVA_PATHS = $(JAVA_BOOTCLASSPATH) $(JAVA_CLASSPATH) $(JAVA_SRCPATH) $(JAVA_BINPATH)
JAVA_FLAGS = $(JAVA_VERSION) $(JAVA_WARNINGS) $(JAVA_OPTIMISATION) $(JAVA_PATHS)

# javah flags
H_CLASSPATH = -classpath bin
H_PATHS = $(JAVA_BOOTCLASSPATH) $(H_CLASSPATH)
H_FLAGS = $(H_PATHS)

# c files
C_SRC = $(shell find . | grep '\.c$$')
C_OBJ = $(shell find . | grep '\.c$$' | sed -e 's/\.c$$/\.o/g' -e 's/^.\//..\/..\/obj\//g')o

# java files
JAVA_SRC = $(shell find . | grep '\.java$$')
JAVA_PRAECLASS = $(shell find . | grep '\.java$$' | sed -e 's/^.\//..\/..\/bin\//g')
JAVA_CLASS = $(shell find . | grep '\.java$$' | sed -e 's/\.java$$/\.class/g' -e 's/^.\//..\/..\/bin\//g')

# h files
#JNI_H =



# compile
.PHONY: all
all: $(EXTENSION)
ifdef JNI_H
all: h
endif

# generate .h
.PHONY: h
h: $(JNI_H)

# compile the extension
.PHONY: $(EXTENSION)
$(EXTENSION): $(EXTENSION).jar
ifdef JNI_H
$(EXTENSION): $(EXTENSION)$(LIB_EXT)
endif

# jpp resolved .java files
../../bin/%.java: %.java
	@mkdir -p "bin"
	$(JPP) --export LIBPATH="$(EXTPATH)" --export LIB="$(LIBFILE)" -s "." -o "../../bin" "$<"

# .class files
../../bin/%.class: ../../bin/%.java
	$(JAVAC) $(JAVA_FLAGS) "$<"

# compile the .jar file
$(EXTENSION).jar: $(JAVA_CLASS)
	jar cf$(JAR_COMPRESS) "$@" $(shell find bin | grep '\.class$$' | sed -e 's:^../../bin/:-C ../../bin :g' -e 's_\$$_"\$$"_g')

# .h files
%.h: %.java
	@sum=$$(md5sum "$@" 2>/dev/null || echo); 									 \
	 echo $(JAVAH) $(H_FLAGS) -o "$@" "$$(echo "$<" | sed -e 's_\.java$$__g' | sed -e 's_/_\._g')";  \
	 $(JAVAH) $(H_FLAGS) -o "$@" "$$(echo "$<" | sed -e 's_\.java$$__g' | sed -e 's_/_\._g')";	 \
	 if [ "$$(grep -v '^#' "$@" | wc -l | cut -d ' ' -f 1)" = 5 ]; then						 \
	     rm "$@";													 \
	 elif [ ! "$$(md5sum "$@" 2>/dev/null || echo)" = "$$sum" ]; then						 \
	     echo -e '\e[01;32m$@ has been updated\e[00m';								 \
	 fi

# .o files
../../obj/%.o: %.c %.h
	@mkdir -p "$$(echo "$@" | sed -e 's_\(.*\)/.*_\1_g')"
	$(CC) $(CFLAGS) $(CPPFLAGS) $(JNI_C_CFLAGS) "$*.c" -c -o "$@"

# compile the .so file
$(EXTENSION)$(LIB_EXT): $(C_OBJ)
	@if [ ! "$@" = "$$(echo "$@" | sed -e s_/__g)" ]; then     \
	     mkdir -p "$$(echo "$@" | sed -e 's_\(.*\)/.*_\1_')";  \
	 fi
	$(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS) $(JNI_C_CFLAGS) $(JNI_C_LDFLAGS) $^ -o "$@"



# install package to $(DESTDIR)
.PHONY: install
install: "$(EXTENSION).jar"
	mkdir -p -- "$(DESTDIR)$(EXTPATH)"
	install -m644 -- "$(EXTENSION).jar" "$(PATH)/$(EXTENSION).jar"
ifdef JNI_H
install: "$(EXTENSION)$(LIB_EXT)"
	install -m755 -- "$(EXTENSION)$(LIB_EXT)" "$(PATH)/$(LIBFILE)"
endif

# uninstall package to $(DESTDIR)
.PHONY: uninstall
uninstall:
	-unlink -- "$(PATH)/$(EXTENSION).jar"
ifdef JNI_H
	-unlink -- "$(PATH)/$(LIBFILE)"
endif
	-rmdir -- "$(PATH)"


# clean up
.PHONY: clean
clean:
	-@rm -r ../../bin ../../obj $$(find . | egrep '/[A-Z].*\.h') 2>/dev/null

