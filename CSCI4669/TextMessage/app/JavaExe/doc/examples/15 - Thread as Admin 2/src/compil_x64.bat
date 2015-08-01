@rem ******************** EXAMPLE 15 - Thread as Admin 2 ********
@echo "compiling EXAMPLE 15 - Thread as Admin 2..."

@copy ..\..\_common_\common.jar ..\ThreadAsAdmin2.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\ThreadAsAdmin2.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example15;..\ThreadAsAdmin2.jar .\com\devwizard\javaexe\examples\example15\*.java
@jar -umf MANIFEST.MF ..\ThreadAsAdmin2.jar .\com\devwizard\javaexe\examples\example15\*.class
@del .\com\devwizard\javaexe\examples\example15\*.class

@cd ..\..
