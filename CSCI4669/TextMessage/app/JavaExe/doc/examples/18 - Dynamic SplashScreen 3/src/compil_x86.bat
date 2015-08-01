@rem ******************** EXAMPLE 18 - Dynamic SplashScreen 3 ********
@echo "compiling EXAMPLE 18 - Dynamic SplashScreen 3..."

@copy ..\..\_common_\common.jar ..\Application18.jar
@copy ..\..\_common_\bin\x86\JavaExe.exe ..\Application18.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example18;..\Application18.jar .\com\devwizard\javaexe\examples\example18\*.java
@jar -umf MANIFEST.MF ..\Application18.jar .\com\devwizard\javaexe\examples\example18\*.class
@del .\com\devwizard\javaexe\examples\example18\*.class

@cd ..
@..\_common_\bin\UpdateRsrcJavaExe.exe -run -exe=Application18.exe -img=src\splash.png
@cd ..
