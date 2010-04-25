@echo off
cd d:\Docs\ChSTU\_Diplom\_myCMS\build\classes\
java -Djavax.net.ssl.keyStore=d:\Docs\ChSTU\_Diplom\_myCMS\srvkey.jks -Djavax.net.ssl.keyStorePassword=srvkey chstu.clans.mycms.server.SSLServer
pause