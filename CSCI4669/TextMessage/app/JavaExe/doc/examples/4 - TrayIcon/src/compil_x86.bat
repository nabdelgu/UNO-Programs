@rem ******************** EXAMPLE 4 - TrayIcon ********
@echo "compiling EXAMPLE 4 - TrayIcon..."

@copy ..\..\_common_\common.jar ..\Example4.jar
@copy ..\..\_common_\bin\x86\JavaExe.exe ..\Example4.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example4;..\Example4.jar .\com\devwizard\javaexe\examples\example4\*.java
@jar -uMf ..\Example4.jar .\com\devwizard\javaexe\examples\example4\*.class
@jar -uMf ..\Example4.jar Images\*.*
@del .\com\devwizard\javaexe\examples\example4\*.class

@cd ..\..
