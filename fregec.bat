@echo off
REM ***
REM Frege compile script for Windows 7
REM ***

setLocal

if "%FREGE_HOME%" == "" (set FREGE_HOME=build)

java -Xmx512m -Xss8m -cp "%FREGE_HOME%;build" frege.compiler.Main -d build -hints %1 %2 %3 %4 %5 %6 %7 %8 %9

endlocal