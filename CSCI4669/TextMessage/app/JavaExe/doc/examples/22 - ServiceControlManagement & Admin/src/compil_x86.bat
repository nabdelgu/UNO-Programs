@rem ******************** EXAMPLE 22 - ServiceControlManagement & Admin ********
@echo "compiling EXAMPLE 22 - ServiceControlManagement & Admin..."

@copy ..\..\_common_\common.jar ..\Example22.jar
@copy ..\..\_common_\bin\x86\JavaExe.exe ..\Example22.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example22;..\Example22.jar .\com\devwizard\javaexe\examples\example22\*.java
@jar -uMf ..\Example22.jar .\com\devwizard\javaexe\examples\example22\*.class
@del .\com\devwizard\javaexe\examples\example22\*.class

@cd ..\..
