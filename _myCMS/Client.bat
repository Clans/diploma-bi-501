@echo off
cd d:\Docs\ChSTU\_Diplom\_myCMS\build\classes\
java -Djavax.net.ssl.trustStore=d:\Docs\ChSTU\_Diplom\_myCMS\clikey.jks -Djavax.net.ssl.trustStorePassword=clikey chstu.clans.mycms.client.Client
pause