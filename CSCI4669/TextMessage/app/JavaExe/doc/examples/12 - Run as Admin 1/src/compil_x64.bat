@rem ******************** EXAMPLE 12 - Run as Admin 1 ********
@echo "compiling EXAMPLE 12 - Run as Admin 1..."

@copy ..\..\_common_\common.jar ..\RunAsAdmin1.jar
@copy ..\..\_common_\bin\x64\JavaExe.exe ..\RunAsAdmin1.exe

@javac -source 1.5 -target 1.5 -g:none -nowarn -classpath .\com\devwizard\javaexe\examples\example12;..\RunAsAdmin1.jar .\com\devwizard\javaexe\examples\example12\*.java
@jar -umf MANIFEST.MF ..\RunAsAdmin1.jar .\com\devwizard\javaexe\examples\example12\*.class
@del .\com\devwizard\javaexe\examples\example12\*.class

@cd ..
@..\_common_\bin\UpdateRsrcJavaExe.exe -run -exe=RunAsAdmin1.exe -admin=true
@cd ..
