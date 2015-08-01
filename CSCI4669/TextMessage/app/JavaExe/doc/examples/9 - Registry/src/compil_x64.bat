@rem ******************** EXAMPLE 9 - Registry ********
@echo "compiling EXAMPLE 9 - Registry..."

@copy ..\..\_common_\common.jar ..\Example9.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\Example9.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example9;..\Example9.jar .\com\devwizard\javaexe\examples\example9\*.java
@jar -uMf ..\Example9.jar .\com\devwizard\javaexe\examples\example9\*.class
@del .\com\devwizard\javaexe\examples\example9\*.class

@cd ..\..
