@rem ******************** EXAMPLE 10 - Test Unicode ********
@echo "compiling EXAMPLE 10 - Test Unicode..."

@copy ..\..\_common_\common.jar ..\TestUnicode.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\TestUnicode.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example10;..\TestUnicode.jar .\com\devwizard\javaexe\examples\example10\*.java
@jar -umf MANIFEST.MF ..\TestUnicode.jar .\com\devwizard\javaexe\examples\example10\*.class
@del .\com\devwizard\javaexe\examples\example10\*.class

@cd ..\..
