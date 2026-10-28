@echo off
if exist out rmdir /s /q out
mkdir out
(for /r src %%f in (*.java) do echo "%%f") > sources.txt
javac -encoding UTF-8 -d out @sources.txt
if errorlevel 1 (
 del sources.txt
 exit /b 1
)
del sources.txt
java -cp out ru.edu.pr02.SelfCheck
