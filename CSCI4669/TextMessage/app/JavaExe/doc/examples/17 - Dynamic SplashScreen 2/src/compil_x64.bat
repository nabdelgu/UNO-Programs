@rem ******************** EXAMPLE 17 - Dynamic SplashScreen 2 ********
@echo "compiling EXAMPLE 17 - Dynamic SplashScreen 2..."

@copy ..\..\_common_\common.jar ..\Application17.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\Application17.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example17;..\Application17.jar .\com\devwizard\javaexe\examples\example17\*.java
@jar -umf MANIFEST.MF ..\Application17.jar .\com\devwizard\javaexe\examples\example17\*.class
@del .\com\devwizard\javaexe\examples\example17\*.class

@cd ..
@..\_common_\bin\UpdateRsrcJavaExe.exe -run -exe=Application17.exe -img=src\splash.gif
@cd ..
