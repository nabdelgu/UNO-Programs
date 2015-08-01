@rem ******************** EXAMPLE 13 - Run as Admin 2 ********
@echo "compiling EXAMPLE 13 - Run as Admin 2..."

@copy ..\..\_common_\common.jar ..\RunAsAdmin2.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\RunAsAdmin2.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example13;..\RunAsAdmin2.jar .\com\devwizard\javaexe\examples\example13\*.java
@jar -umf MANIFEST.MF ..\RunAsAdmin2.jar .\com\devwizard\javaexe\examples\example13\*.class
@del .\com\devwizard\javaexe\examples\example13\*.class

@cd ..\..
