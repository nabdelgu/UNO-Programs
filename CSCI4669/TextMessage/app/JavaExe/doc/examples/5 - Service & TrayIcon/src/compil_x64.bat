@rem ******************** EXAMPLE 5 - Service & TrayIcon *************
@echo "compiling EXAMPLE 5 - Service & TrayIcon..."

@copy ..\..\_common_\common.jar ..\resource\Example5.jar
@copy ..\..\_common_\commonService.jar ..\resource\
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\Example5.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example5;..\resource\Example5.jar;..\resource\commonService.jar .\com\devwizard\javaexe\examples\example5\*.java
@jar -uMf ..\resource\Example5.jar .\com\devwizard\javaexe\examples\example5\*.class
@del .\com\devwizard\javaexe\examples\example5\*.class

@cd ..
@call createShortcut.bat
@cd ..
