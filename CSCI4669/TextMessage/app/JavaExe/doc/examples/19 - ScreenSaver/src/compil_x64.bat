@rem ******************** EXAMPLE 19 - ScreenSaver ********
@echo "compiling EXAMPLE 19 - ScreenSaver..."

@copy ..\..\_common_\common.jar ..\ScreenSaver.jar
@copy ..\..\_common_\bin\x64\JavaExe.scr ..\ScreenSaver.scr
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\ScreenSaver.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example19;..\ScreenSaver.jar .\com\devwizard\javaexe\examples\example19\*.java
@jar -umf MANIFEST.MF ..\ScreenSaver.jar .\com\devwizard\javaexe\examples\example19\*.class
@jar -uf ..\ScreenSaver.jar Images\*.*
@del .\com\devwizard\javaexe\examples\example19\*.class

@cd ..
@call createShortcut.bat
@cd ..
