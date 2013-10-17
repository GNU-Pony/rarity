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
#    DEBUG        --  Compile with DEBUG defined


# settings
OPTIMISATION = -g
JAR_COMPRESS = 0

# install settings
PKGNAME = rarity
JAVA_PREFIX = /usr
JAVA_BINDIR = /bin
JAVA = java
JAVAPATH = $(JAVA_PREFIX)$(JAVA_BINDIR)/$(JAVA)
SHEBANG = $(JAVAPATH) -jar
ifdef TESTING
  LIBPATH = .
else
  LIBPATH=$(PREFIX)/lib
endif
PREFIX = /usr
BINDIR = /bin
DATADIR = /share
BINPATH = $(PREFIX)$(BINDIR)
DATAPATH= $(PREFIX)$(DATADIR)
LICENSEPATH = $(DATAPATH)/licenses/$(PKGNAME)
INFOPATH = $(DATAPATH)/info
COMMAND = rarity

# PLATFORM DEPENDENT: the file extension for library files
LIB_EXT = .so

# the library name
LIB = rarity

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
CFLAGS = $(C_WARNINGS) $(C_OPTIMISATION) -std=$(C_STD) $(shell "$(PKG_CONFIG)" --cflags x11 xrandr)
CPPFLAGS =
LDFLAGS = $(shell "$(PKG_CONFIG)" --libs x11 xrandr)
ifdef NO_XINERAMA
  CPPFLAGS += -DNO_XINERAMA
else
  CFLAGS += $(shell "$(PKG_CONFIG)" --cflags xinerama)
  LDFLAGS += $(shell "$(PKG_CONFIG)" --libs xinerama)
endif
ifdef DEBUG
  CPPFLAGS += -DDEBUG
endif

# cc flags for jni
JNI_INCLUDE = -I$(shell echo $${JAVA_HOME})/include
JNI_C_CFLAGS = $(JNI_INCLUDE) -fPIC
JNI_C_LDFLAGS = -shared

# jpp flags
JPP_SRCPATH = -s "src"
JPP_BINPATH = -o "bin"
JPP_EXPORTS = --export LIBPATH="$(LIBPATH)" --export LIB="$(LIB_PREFIX)$(LIB)$(LIB_EXT)"
ifdef DEBUG
  JPP_EXPORTS += --export DEBUG
endif
JPP_FLAGS = $(JPP_EXPORTS) $(JPP_SRCPATH) $(JPP_BINPATH)

# javac flags
JAVA_VERSION = -source $(JAVA_SOURCE) -target $(JAVA_TARGET)
JAVA_WARNINGS = -Xlint
JAVA_OPTIMISATION = $(shell echo '$(OPTIMISATION)' | sed -e 's/[1-6]//g' -e 's/-O0//g')
JAVA_BOOTCLASSPATH =
JAVA_CLASSPATH = -classpath bin
JAVA_SRCPATH = -s bin
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
JAVA_PRAECLASS = $(shell find src | grep '\.java$$' | sed -e 's/^src\//bin\//g')
JAVA_CLASS = $(shell find src | grep '\.java$$' | sed -e 's/\.java$$/\.class/g' -e 's/^src\//bin\//g')

# h files
JNI_H = X11 Xinerama Rarity Signals XKeyboard Window Monitor XAtom

# so files
LIB_PREFIX = 
LIB_RARITY = bin/$(LIB_PREFIX)$(LIB)$(LIB_EXT)

# jar file
JAR_RARITY = bin/$(LIB).jar

# misc files
MANIFEST = META-INF/MANIFEST.MF

# extensions
EXTENSIONS = nightmare



# compile
.PHONY: all
all: rarity extensions

# generate .h
.PHONY: h
h: $(foreach H, $(JNI_H), src/rarity/$(H).h)

# compile rarity
.PHONY: rarity
rarity: $(LIB_RARITY) $(JAVA_PRAECLASS) bin/rarity.install

# compile extensions
.PHONY: extensions
extensions:
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@for e in $(EXTENSIONS); do       \
	     make -C "extensions/$${e}";  \
	 done


# .o files
obj/%.o: src/%.c src/%.h
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@mkdir -p "$$(echo "$@" | sed -e 's_\(.*\)/.*_\1_g')"
	$(CC) $(CFLAGS) $(CPPFLAGS) $(JNI_C_CFLAGS) "$<" -c -o "$@"

# .so file
$(LIB_RARITY): $(C_OBJ)
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@if [ ! "$@" = "$$(echo "$@" | sed -e s_/__g)" ]; then     \
	     mkdir -p "$$(echo "$@" | sed -e 's_\(.*\)/.*_\1_')";  \
	 fi
	$(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS) $(JNI_C_CFLAGS) $(JNI_C_LDFLAGS) $^ -o "$@"

# jpp resolved .java files
bin/%.java: src/%.java
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@mkdir -p "bin"
	$(JPP) $(JPP_FLAGS) "$<"

# .class files
bin/%.class: $(JAVA_PRAECLASS)
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	$(JAVAC) $(JAVA_FLAGS) "bin/$*.java"

# .h files
src/%.h: bin/%.class
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	@sum=$$(md5sum "$@" 2>/dev/null || echo); 									 \
	 echo $(JAVAH) $(H_FLAGS) -o "$@" "$$(echo "$<" | sed -e 's_^bin/__g' -e 's_\.class$$__g' | sed -e 's_/_\._g')";  \
	 $(JAVAH) $(H_FLAGS) -o "$@" "$$(echo "$<" | sed -e 's_^bin/__g' -e 's_\.class$$__g' | sed -e 's_/_\._g')";	 \
	 if [ "$$(grep -v '^#' "$@" | wc -l | cut -d ' ' -f 1)" = 5 ]; then						 \
	     rm "$@";													 \
	 elif [ ! "$$(md5sum "$@" 2>/dev/null || echo)" = "$$sum" ]; then						 \
	     echo -e '\e[01;32m$@ has been updated\e[00m';								 \
	 fi

# .jar file
$(JAR_RARITY): $(JAVA_CLASS) $(MANIFEST)
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	jar cfm$(JAR_COMPRESS) "$@" $(MANIFEST) $(shell find bin | grep '\.class$$' | sed -e 's:^bin/:-C bin :g' -e 's_\$$_"\$$"_g')

# command file
bin/rarity.install: $(JAR_RARITY)
	@echo -e '\e[34;01m$@\e[21m: $^\e[00m'
	(echo "#!$(SHEBANG)" ; cat "$<") > "$@"
	chmod a+x "$@"


# install package to $(DESTDIR)
.PHONY: install
install: $(LIB_RARITY) bin/rarity.install
	mkdir -p -- "$(DESTDIR)$(LIBPATH)"
	mkdir -p -- "$(DESTDIR)$(BINPATH)"
	mkdir -p -- "$(DESTDIR)$(LICENSEPATH)"
	install -m755 -- "$(LIB_RARITY)" "$(DESTDIR)$(LIBPATH)"
	install -m755 -- "bin/rarity.install" "$(DESTDIR)$(BINPATH)/$(COMMAND)"
	install -m644 -- "COPYING" "$(DESTDIR)$(LICENSEPATH)"
	install -m644 -- "LICENSE" "$(DESTDIR)$(LICENSEPATH)"

# uninstall package to $(DESTDIR)
.PHONY: uninstall
uninstall:
	-unlink -- "$(DESTDIR)$(LIBPATH)/$(LIB_PREFIX)$(LIB)$(LIB_EXT)"
	-unlink -- "$(DESTDIR)$(BINPATH)/$(COMMAND)"
	-unlink -- "$(DESTDIR)$(LICENSEPATH)/COPYING"
	-unlink -- "$(DESTDIR)$(LICENSEPATH)/LICENSE"
	-rmdir -- "$(DESTDIR)$(LIBPATH)"
	-rmdir -- "$(DESTDIR)$(BINPATH)"
	-rmdir -- "$(DESTDIR)$(LICENSEPATH)"


# clean up
.PHONY: clean
clean:
	-@rm -r bin obj $$(find src | egrep '/[A-Z].*\.h') 2>/dev/null
	@for e in $(EXTENSIONS); do             \
	     make -C "extensions/$${e}" clean;  \
	 done

