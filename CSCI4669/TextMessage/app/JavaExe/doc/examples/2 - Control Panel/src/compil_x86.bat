@rem ******************** EXAMPLE 2 - ControlPanel ********
@echo "compiling EXAMPLE 2 - ControlPanel..."

@copy ..\..\_common_\common.jar ..\Example2.jar
@copy ..\..\_common_\bin\x86\JavaExe.exe ..\Example2.exe
@copy ..\..\_common_\bin\x86\JavaExe.cpl ..\Example2.cpl

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example2;..\Example2.jar .\com\devwizard\javaexe\examples\example2\*.java
@jar -uMf ..\Example2.jar .\com\devwizard\javaexe\examples\example2\*.class
@jar -uMf ..\Example2.jar .\Images\*.*
@del .\com\devwizard\javaexe\examples\example2\*.class

@cd ..
@..\_common_\bin\UpdateRsrcJavaExe.exe -run -exe=Example2.exe -prp=src\liste.properties
@call createShortcut.bat
@cd ..
