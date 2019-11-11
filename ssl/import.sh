#!/bin/sh

sudo keytool -trustcacerts -keystore /usr/lib/jvm/java-11-openjdk-amd64/lib/security/cacerts -storepass changeit -alias payara -import -file ./payara-self-signed-certificate.crt
