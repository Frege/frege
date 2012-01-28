@echo off
REM ***
REM Frege runscript for Windows 7
REM ***

setLocal

if "%FREGE_HOME%" == "" (set FREGE_HOME=build)

java -cp "%FREGE_HOME%;build" %1 %2 %3 %4 %5 %6 %7 %8 %9

endlocal