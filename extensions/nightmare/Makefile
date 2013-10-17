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
DESTPATH = $(DESTDIR)$(EXTPATH)

# PLATFORM DEPENDENT: the file extension for library files
LIB_EXT = .so

# the library file without directory
LIBFILE = $(LIB_PREFIX)$(EXTENSION)$(LIB_EXT)

# the bin and obj directories
BIN = bin
OBJ = obj
SED_BIN = $(shell echo $(BIN) | sed -e 's:\/:\\/:g' -e 's:\.:\\\.:g')
SED_OBJ = $(shell echo $(OBJ) | sed -e 's:\/:\\/:g' -e 's:\.:\\\.:g')
ROOT_BIN = ../../bin
ROOT_OBJ = ../../obj
SED_ROOT_BIN = $(shell echo $(ROOT_BIN) | sed -e 's:\/:\\/:g' -e 's:\.:\\\.:g')
SED_ROOT_OBJ = $(shell echo $(ROOT_OBJ) | sed -e 's:\/:\\/:g' -e 's:\.:\\\.:g')

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
JAVA_CLASSPATH = -classpath $(BIN):$(ROOT_BIN)/rarity.jar
JAVA_SRCPATH = -s $(BIN)
JAVA_BINPATH = -d $(BIN)
JAVA_PATHS = $(JAVA_BOOTCLASSPATH) $(JAVA_CLASSPATH) $(JAVA_SRCPATH) $(JAVA_BINPATH)
JAVA_FLAGS = $(JAVA_VERSION) $(JAVA_WARNINGS) $(JAVA_OPTIMISATION) $(JAVA_PATHS)

# javah flags
H_CLASSPATH = -classpath $(BIN)
H_PATHS = $(JAVA_BOOTCLASSPATH) $(H_CLASSPATH)
H_FLAGS = $(H_PATHS)

# c files
C_SRC = $(shell find . | grep '\.c$$')
C_OBJ = $(shell find . | grep '\.c$$' | sed -e 's/\.c$$/\.o/g' -e 's/^\.\//$(SED_OBJ)\//g')

# java files
SRC = ./$(shell echo $(EXTENSION) | sed -e 's:\.:\/:g')
JAVA_SRC = $(shell find $(SRC) | grep '\.java$$')
JAVA_PRAECLASS = $(shell find $(SRC) | grep '\.java$$' | sed -e 's/^\.\//$(SED_BIN)\//g')
JAVA_CLASS = $(shell find $(SRC) | grep '\.java$$' | sed -e 's/\.java$$/\.class/g' -e 's/^\.\//$(SED_BIN)\//g')

# h files
#JNI_H =



# compile
.PHONY: all
all: $(EXTENSION)

# generate .h
.PHONY: h
h: $(JNI_H)

# compile the extension
.PHONY: $(EXTENSION)
$(EXTENSION): $(BIN)/$(EXTENSION).jar
ifdef JNI_H
$(EXTENSION): $(BIN)/$(EXTENSION)$(LIB_EXT)
endif

# jpp resolved .java files
$(BIN)/%.java: %.java
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@mkdir -p "$(BIN)"
	$(JPP) --export LIBPATH="$(EXTPATH)" --export LIB="$(LIBFILE)" -s "." -o "$(BIN)" "$<"

# .class files
$(BIN)/%.class: $(JAVA_PRAECLASS)
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	$(JAVAC) $(JAVA_FLAGS) "$(BIN)/$*.java"

# compile the .jar file
$(BIN)/$(EXTENSION).jar: $(JAVA_PRAECLASS) $(JAVA_CLASS)
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	jar cf$(JAR_COMPRESS) "$@" $(shell find $(BIN) | grep '\.class$$' | sed -e 's:^$(BIN)\/:-C $(BIN) :g' -e 's_\$$_"\$$"_g')

# .h files
%.h: bin/%.class
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@sum=$$(md5sum "$@" 2>/dev/null || echo); 									 \
	 echo $(JAVAH) $(H_FLAGS) -o "$@" "$$(echo "$<" | sed -e 's_^bin/__g' -e 's_\.class$$__g' | sed -e 's_/_\._g')";  \
	 $(JAVAH) $(H_FLAGS) -o "$@" "$$(echo "$<" | sed -e 's_^bin/__g' -e 's_\.class$$__g' | sed -e 's_/_\._g')";	 \
	 if [ "$$(grep -v '^#' "$@" | wc -l | cut -d ' ' -f 1)" = 5 ]; then						 \
	     rm "$@";													 \
	 elif [ ! "$$(md5sum "$@" 2>/dev/null || echo)" = "$$sum" ]; then						 \
	     echo -e '\e[01;32m$@ has been updated\e[00m';								 \
	 fi

# .o files
$(OBJ)/%.o: %.c %.h
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@mkdir -p "$$(echo "$@" | sed -e 's_\(.*\)/.*_\1_g')"
	$(CC) $(CFLAGS) $(CPPFLAGS) $(JNI_C_CFLAGS) "$*.c" -c -o "$@"

# compile the .so file
$(BIN)/$(EXTENSION)$(LIB_EXT): $(C_OBJ)
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@if [ ! "$@" = "$$(echo "$@" | sed -e s_/__g)" ]; then     \
	     mkdir -p "$$(echo "$@" | sed -e 's_\(.*\)/.*_\1_')";  \
	 fi
	$(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS) $(JNI_C_CFLAGS) $(JNI_C_LDFLAGS) $^ -o "$@"



# install package to $(DESTDIR)
.PHONY: install
install: "$(BIN)/$(EXTENSION).jar"
	mkdir -p -- "$(DESTDIR)$(EXTPATH)"
	install -m644 -- "$(BIN)/$(EXTENSION).jar" "$(DESTPATH)/$(EXTENSION).jar"
ifdef JNI_H
install: "$(BIN)/$(EXTENSION)$(LIB_EXT)"
	install -m755 -- "$(BIN)/$(EXTENSION)$(LIB_EXT)" "$(DESTPATH)/$(LIBFILE)"
endif

# uninstall package to $(DESTDIR)
.PHONY: uninstall
uninstall:
	-unlink -- "$(DESTPATH)/$(EXTENSION).jar"
ifdef JNI_H
	-unlink -- "$(DESTPATH)/$(LIBFILE)"
endif
	-rmdir -- "$(DESTPATH)"


# clean up
.PHONY: clean
clean:
	-@rm -r "$(BIN)" "$(OBJ)" $$(find . | egrep '/[A-Z].*\.h') 2>/dev/null

