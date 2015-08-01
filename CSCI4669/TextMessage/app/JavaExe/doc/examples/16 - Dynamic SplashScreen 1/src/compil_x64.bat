@rem ******************** EXAMPLE 16 - Dynamic SplashScreen 1 ********
@echo "compiling EXAMPLE 16 - Dynamic SplashScreen 1..."

@copy ..\..\_common_\common.jar ..\Application16.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\Application16.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example16;..\Application16.jar .\com\devwizard\javaexe\examples\example16\*.java
@jar -umf MANIFEST.MF ..\Application16.jar .\com\devwizard\javaexe\examples\example16\*.class
@del .\com\devwizard\javaexe\examples\example16\*.class

@cd ..
@..\_common_\bin\UpdateRsrcJavaExe.exe -run -exe=Application16.exe -img=src\splash.jpg
@cd ..
