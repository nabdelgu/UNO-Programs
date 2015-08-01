@rem ******************** EXAMPLE 21 - ServiceControlManagement ********
@echo "compiling EXAMPLE 21 - ServiceControlManagement..."

@copy ..\..\_common_\common.jar ..\Example21.jar
@copy ..\..\_common_\bin\x86\JavaExe.exe ..\Example21.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example21;..\Example21.jar .\com\devwizard\javaexe\examples\example21\*.java
@jar -uMf ..\Example21.jar .\com\devwizard\javaexe\examples\example21\*.class
@del .\com\devwizard\javaexe\examples\example21\*.class

@cd ..\..
