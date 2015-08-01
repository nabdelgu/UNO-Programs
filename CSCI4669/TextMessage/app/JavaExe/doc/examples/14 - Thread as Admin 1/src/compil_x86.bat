@rem ******************** EXAMPLE 14 - Thread as Admin 1 ********
@echo "compiling EXAMPLE 14 - Thread as Admin 1..."

@copy ..\..\_common_\common.jar ..\ThreadAsAdmin1.jar
@copy ..\..\_common_\bin\x86\JavaExe.exe ..\ThreadAsAdmin1.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example14;..\ThreadAsAdmin1.jar .\com\devwizard\javaexe\examples\example14\*.java
@jar -umf MANIFEST.MF ..\ThreadAsAdmin1.jar .\com\devwizard\javaexe\examples\example14\*.class
@del .\com\devwizard\javaexe\examples\example14\*.class

@cd ..\..
