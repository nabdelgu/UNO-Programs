@rem ******************** EXAMPLE 1 - Application ********
@echo "compiling EXAMPLE 1 - Application..."

@copy ..\..\_common_\common.jar ..\Application1.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\Application1.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example1;..\Application1.jar .\com\devwizard\javaexe\examples\example1\*.java
@jar -umf MANIFEST.MF ..\Application1.jar .\com\devwizard\javaexe\examples\example1\*.class
@del .\com\devwizard\javaexe\examples\example1\*.class

@cd ..
@..\_common_\bin\UpdateRsrcJavaExe.exe -run -exe=Application1.exe -bmp=src\splash.bmp
@cd ..
