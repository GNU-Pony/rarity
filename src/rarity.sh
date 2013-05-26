#!%SHEBANG

if [ ! "%LIBPATH" = "" ]; then
    export LD_LIBRARY_PATH="%LIBPATH:${LD_LIBRARY_PATH}"
fi
"%JAVA" -jar "%JARPATH/rarity.jar" "$@"

