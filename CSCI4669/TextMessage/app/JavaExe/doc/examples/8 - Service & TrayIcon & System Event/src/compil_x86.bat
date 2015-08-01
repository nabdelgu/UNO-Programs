@rem ******************** EXAMPLE 8 - Service & TrayIcon & System Event *******
@echo "compiling EXAMPLE 8 - Service & TrayIcon & System Event..."

@copy ..\..\_common_\common.jar ..\resource\Example8.jar
@copy ..\..\_common_\commonEvent.jar ..\resource\
@copy ..\..\_common_\bin\x86\JavaExe.exe ..\Example8.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example8;..\resource\Example8.jar;..\resource\commonEvent.jar .\com\devwizard\javaexe\examples\example8\*.java
@jar -uMf ..\resource\Example8.jar .\com\devwizard\javaexe\examples\example8\*.class
@del .\com\devwizard\javaexe\examples\example8\*.class

@cd ..
@call createShortcut.bat
@cd ..
