@rem ******************** EXAMPLE 23 - Service & TrayIcon & System Event & SCM & Admin *******
@echo "compiling EXAMPLE 23 - Service & TrayIcon & System Event & SCM & Admin..."

@copy ..\..\_common_\common.jar ..\resource\Example23.jar
@copy ..\..\_common_\commonEvent.jar ..\resource\
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\Example23.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example23;..\resource\Example23.jar;..\resource\commonEvent.jar .\com\devwizard\javaexe\examples\example23\*.java
@jar -uMf ..\resource\Example23.jar .\com\devwizard\javaexe\examples\example23\*.class
@del .\com\devwizard\javaexe\examples\example23\*.class

@cd ..
@call createShortcut.bat
@cd ..
