@rem ******************** EXAMPLE 11 - Restore Session ********
@echo "compiling EXAMPLE 11 - Restore Session..."

@copy ..\..\_common_\common.jar ..\RestoreSession.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\RestoreSession.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example11;..\RestoreSession.jar .\com\devwizard\javaexe\examples\example11\*.java
@jar -umf MANIFEST.MF ..\RestoreSession.jar .\com\devwizard\javaexe\examples\example11\*.class
@del .\com\devwizard\javaexe\examples\example11\*.class

@cd ..\..
