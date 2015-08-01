@rem ******************** EXAMPLE 7 - OneInstance ********
@echo "compiling EXAMPLE 7 - OneInstance..."

@copy ..\..\_common_\common.jar ..\OneInstance.jar
@copy ..\..\_common_\bin\x86\JavaExe.exe ..\OneInstance.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example7;..\OneInstance.jar .\com\devwizard\javaexe\examples\example7\*.java
@jar -umf MANIFEST.MF ..\OneInstance.jar .\com\devwizard\javaexe\examples\example7\*.class
@del .\com\devwizard\javaexe\examples\example7\*.class

@cd ..\..
