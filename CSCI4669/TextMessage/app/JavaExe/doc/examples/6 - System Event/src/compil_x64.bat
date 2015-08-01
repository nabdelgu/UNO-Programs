@rem ******************** EXAMPLE 6 - SystemEvent *********
@echo "compiling EXAMPLE 6 - SystemEvent..."

@copy ..\..\_common_\common.jar ..\resource\Example6.jar
@copy ..\..\_common_\commonEvent.jar ..\resource\
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\Example6.exe
@copy ..\..\_common_\bin\x64\JavaExe_console.exe ..\Example6_console.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example6;..\resource\Example6.jar;..\resource\commonEvent.jar .\com\devwizard\javaexe\examples\example6\*.java
@jar -uMf ..\resource\Example6.jar .\com\devwizard\javaexe\examples\example6\*.class
@del .\com\devwizard\javaexe\examples\example6\*.class

@cd ..\..
