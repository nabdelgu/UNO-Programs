@rem ******************** EXAMPLE 20 - SystemManagement ********
@echo "compiling EXAMPLE 20 - SystemManagement..."

@copy ..\..\_common_\common.jar ..\Example20.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\Example20.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example20;..\Example20.jar .\com\devwizard\javaexe\examples\example20\*.java
@jar -uMf ..\Example20.jar .\com\devwizard\javaexe\examples\example20\*.class
@del .\com\devwizard\javaexe\examples\example20\*.class

@cd ..\..
